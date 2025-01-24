package com.amarinag.randomuser.core.data.repository

import androidx.paging.PagingData
import com.amarinag.randomuser.core.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(query: String?): Flow<PagingData<User>>
    fun getUserByEmail(email: String): Flow<User>
}