package com.amarinag.randomuser.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.amarinag.core.database.dao.UserDao
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.network.RandomUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnlineUserRepository @Inject constructor(
    private val network: RandomUserDataSource,
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(query: String?): Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 3,
            initialLoadSize = 30,
        ),
        pagingSourceFactory = { UserPagingSource(network, query) }
    ).flow

    override fun getUserByEmail(email: String): Flow<User> = emptyFlow()
//        flowOf(users.first { it.email == email })
}