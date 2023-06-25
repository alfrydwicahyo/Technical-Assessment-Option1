package com.example.technical_assessment_option1.data.common.module

import com.example.technical_assessment_option1.BuildConfig
import com.example.technical_assessment_option1.constants.Constant
import com.example.technical_assessment_option1.data.common.utils.RequestInterceptor
import com.example.technical_assessment_option1.utils.SharedPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkhttp(requestInterceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(Constant.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            readTimeout(Constant.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            writeTimeout(Constant.TIMEOUT_SECONDS, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
        }.build()
    }

    @Provides
    fun provideRequestInterceptor(prefs: SharedPrefs): RequestInterceptor {
        return RequestInterceptor(prefs)
    }

}