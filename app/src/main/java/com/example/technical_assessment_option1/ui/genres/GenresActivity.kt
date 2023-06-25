package com.example.technical_assessment_option1.ui.genres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.technical_assessment_option1.R
import com.example.technical_assessment_option1.databinding.ActivityGenresBinding
import com.example.technical_assessment_option1.domain.genres.entity.GenresEntity
import com.example.technical_assessment_option1.domain.helper.GeneralHelper
import com.example.technical_assessment_option1.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class GenresActivity : AppCompatActivity(), GenreAdapter.OnCallback {

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    private lateinit var binding: ActivityGenresBinding

    private val viewModel: GenresViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GeneralHelper.setToolbar(
            this,
            binding.toolbar.toolbar,
            GeneralHelper.getString(this, R.string.movies_db)
        )

        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
            }
        }
        binding.rectangles.layoutManager = layoutManager


        observe()
    }

    private fun observe() {
        viewModel.mState
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }
            .launchIn(lifecycleScope)

        viewModel.getGenres()
    }

    private fun handleStateChange(state: GenresActivityState) {
        when (state) {
            is GenresActivityState.Init -> Unit
            is GenresActivityState.SuccessGetGenres -> handleSuccessGenres(state.genresEntity)
            is GenresActivityState.ShowToast -> Toast.makeText(
                applicationContext,
                "" + state.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun handleSuccessGenres(genresEntity: MutableList<GenresEntity>) {
        binding.rectangles.adapter = GenreAdapter(genresEntity, this)
    }

    override fun onItemClick(id: Int) {

    }
}