package com.example.technical_assessment_option1.ui.genres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technical_assessment_option1.domain.common.BaseResult
import com.example.technical_assessment_option1.domain.genres.entity.GenresEntity
import com.example.technical_assessment_option1.domain.genres.usecase.GenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(private val genresUseCase: GenresUseCase) : ViewModel() {
    private val state = MutableStateFlow<GenresActivityState>(GenresActivityState.Init)
    val mState: StateFlow<GenresActivityState> get() = state

    private fun showToast(message: String) {
        state.value = GenresActivityState.ShowToast(message)
    }

    fun getGenres() {
        viewModelScope.launch {
            genresUseCase.execute()
                .onStart { }
                .catch { exception ->
                    Log.d("RangerWhite007", "getGenres: $exception")
                    showToast(exception.message.toString())

                }
                .collect { baseResult ->
                    when (baseResult) {
                        is BaseResult.Error ->
                            showToast(baseResult.rawResponse.toString())
                        is BaseResult.Success -> {
                            val genresResponse = baseResult.data
                            val genresEntityList =
                                genresResponse.map { genreResponse ->
                                    GenresEntity(genreResponse.id, genreResponse.name)
                                } as MutableList<GenresEntity>

                            state.value = GenresActivityState.SuccessGetGenres(genresEntityList)
                        }
                    }
                }
        }
    }
}

sealed class GenresActivityState {
    object Init : GenresActivityState()

    data class SuccessGetGenres(val genresEntity: MutableList<GenresEntity>) : GenresActivityState()

    data class ShowToast(val message: String) : GenresActivityState()
}