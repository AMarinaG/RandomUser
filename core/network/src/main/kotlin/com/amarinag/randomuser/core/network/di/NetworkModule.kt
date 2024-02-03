package com.amarinag.randomuser.core.network.di

import com.amarinag.randomuser.core.network.RandomUserDataSource
import com.amarinag.randomuser.core.network.retrofit.RetrofitRandomUserNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesOkHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        .build()

    @Provides
    @Singleton
    fun providesNetworkDataSource(impl: RetrofitRandomUserNetwork): RandomUserDataSource = impl

}