package com.amarinag.randomuser.core.network.retrofit

import com.amarinag.randomuser.core.network.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRandomUserApi {
    @GET("api/")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("seed") seed: String? = null,
        @Query("results") results: Int = 10
    ): NetworkResponse
}