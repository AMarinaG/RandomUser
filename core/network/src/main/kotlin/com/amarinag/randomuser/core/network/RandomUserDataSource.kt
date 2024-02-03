package com.amarinag.randomuser.core.network

import com.amarinag.randomuser.core.network.model.NetworkUser

interface RandomUserDataSource {
    suspend fun getUsers(page: Int = 0): List<NetworkUser>
}