package com.amarinag.randomuser.core.network

import com.amarinag.randomuser.core.network.model.NetworkResponse

interface RandomUserDataSource {
    suspend fun getUsers(page: Int, seed: String? = null, results: Int = 10): NetworkResponse
}