package com.example.technical_assessment_option1.domain.genres.usecase

import com.example.technical_assessment_option1.domain.common.BaseResult
import com.example.technical_assessment_option1.domain.genres.GenresRepository
import com.example.technical_assessment_option1.domain.genres.entity.GenresEntity
import com.example.technical_assessment_option1.model.response.GenresResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenresUseCase @Inject constructor(private val genresRepository: GenresRepository) {
    suspend fun execute(): Flow<BaseResult<MutableList<GenresEntity>, GenresResponse>> {
        return genresRepository.genres()
    }
}