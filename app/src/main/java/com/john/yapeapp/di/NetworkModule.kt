package com.john.yapeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit.SECONDS
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(DEFAULT_TIMEOUT, SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, SECONDS)
            .connectTimeout(DEFAULT_TIMEOUT, SECONDS)
            .build()
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DEFAULT_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private const val DEFAULT_TIMEOUT = 10L
    private const val DEFAULT_URL = "http://demo3917418.mockable.io/"
}