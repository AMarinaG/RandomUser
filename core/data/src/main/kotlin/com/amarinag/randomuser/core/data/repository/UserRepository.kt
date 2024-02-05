package com.amarinag.randomuser.core.data.repository

import com.amarinag.randomuser.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    fun getUserByEmail(email: String): Flow<User>
}