package com.example.technical_assessment_option1.data.common.utils

import com.example.technical_assessment_option1.constants.Constant
import com.example.technical_assessment_option1.utils.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor constructor(private val pref: SharedPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = Constant.AUTHORIZATION
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(newRequest)
    }
}