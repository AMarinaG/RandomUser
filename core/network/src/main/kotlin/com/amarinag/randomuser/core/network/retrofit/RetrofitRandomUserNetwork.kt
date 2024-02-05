package com.amarinag.randomuser.core.network.retrofit

import com.amarinag.randomuser.core.network.BuildConfig
import com.amarinag.randomuser.core.network.RandomUserDataSource
import com.amarinag.randomuser.core.network.model.NetworkResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitRandomUserNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : RandomUserDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(RetrofitRandomUserApi::class.java)

    override suspend fun getUsers(page: Int, seed: String?): NetworkResponse =
        networkApi.getUsers(page, seed)
}