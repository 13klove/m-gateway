package com.m.one.mgateway.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class RetrofitConfig(
    @Value("\${service.auth.url}")
    private val authUrl: String
) {

    @Bean
    fun mAuthService(): Retrofit = Retrofit.Builder()
        .baseUrl(authUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}