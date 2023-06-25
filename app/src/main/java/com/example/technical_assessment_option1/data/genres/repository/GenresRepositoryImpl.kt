package com.example.technical_assessment_option1.data.genres.repository

import com.example.technical_assessment_option1.data.genres.api.GenresApi
import com.example.technical_assessment_option1.domain.common.BaseResult
import com.example.technical_assessment_option1.domain.genres.GenresRepository
import com.example.technical_assessment_option1.domain.genres.entity.GenresEntity
import com.example.technical_assessment_option1.model.response.GenresResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(private val genresApi: GenresApi) :
    GenresRepository {

    override suspend fun genres(): Flow<BaseResult<MutableList<GenresEntity>, GenresResponse>> {
        return flow {
            val response = genresApi.genres()
            if (response.isSuccessful) {
                val body = response.body()
                val genresResponse = body!!.genres
                val genresEntityList =
                    genresResponse.map { genreResponse ->
                        GenresEntity(genreResponse.id, genreResponse.name)
                    } as MutableList<GenresEntity>
                emit(BaseResult.Success(genresEntityList))
            } else {
                val type = object : TypeToken<GenresResponse>() {}.type
                val err: GenresResponse =
                    Gson().fromJson(response.errorBody()!!.charStream(), type)
                emit(BaseResult.Error(err))
            }
        }
    }

}