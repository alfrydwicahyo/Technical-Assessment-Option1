package com.example.technical_assessment_option1.domain.genres

import com.example.technical_assessment_option1.domain.common.BaseResult
import com.example.technical_assessment_option1.domain.genres.entity.GenresEntity
import com.example.technical_assessment_option1.model.response.GenresResponse
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    suspend fun genres(): Flow<BaseResult<MutableList<GenresEntity>, GenresResponse>>
}