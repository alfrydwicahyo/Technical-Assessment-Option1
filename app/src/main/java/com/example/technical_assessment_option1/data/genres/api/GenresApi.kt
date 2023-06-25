package com.example.technical_assessment_option1.data.genres.api

import com.example.technical_assessment_option1.model.response.GenresResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenresApi {
    @GET("genre/movie/list")
    suspend fun genres(): Response<GenresResponse>
}