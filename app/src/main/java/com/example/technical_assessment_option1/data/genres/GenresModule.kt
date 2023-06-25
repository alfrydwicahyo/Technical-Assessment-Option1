package com.example.technical_assessment_option1.data.genres

import com.example.technical_assessment_option1.data.common.module.RestModule
import com.example.technical_assessment_option1.data.genres.api.GenresApi
import com.example.technical_assessment_option1.data.genres.repository.GenresRepositoryImpl
import com.example.technical_assessment_option1.domain.genres.GenresRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RestModule::class])
@InstallIn(SingletonComponent::class)
class GenresModule {
    @Singleton
    @Provides
    fun provideGenresApi(retrofit: Retrofit): GenresApi {
        return retrofit.create(GenresApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGenresRepository(genresApi: GenresApi): GenresRepository {
        return GenresRepositoryImpl(genresApi)
    }
}