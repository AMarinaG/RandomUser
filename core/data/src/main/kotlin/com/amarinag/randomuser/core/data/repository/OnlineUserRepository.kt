package com.amarinag.randomuser.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.amarinag.core.database.dao.UserDao
import com.amarinag.core.database.model.UserEntity
import com.amarinag.core.database.model.asModel
import com.amarinag.core.datastore.RandomUserPreferenceDataSource
import com.amarinag.randomuser.core.data.paging.UserRemoteMediator
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.network.RandomUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class OnlineUserRepository @Inject constructor(
    private val network: RandomUserDataSource,
    private val userDao: UserDao,
    private val dataStore: RandomUserPreferenceDataSource,
) : UserRepository {

    private val pagingSourceFactory = { userDao.getUsers() }
    override fun getUsers(query: String?): Flow<PagingData<User>> = Pager<Int, UserEntity>(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 3,
            initialLoadSize = 50,
        ),
        remoteMediator = UserRemoteMediator(
            query,
            network = network,
            userDao = userDao,
            dataStore = dataStore
        ),
        pagingSourceFactory = pagingSourceFactory
    ).flow.map { pagingData -> pagingData.map { it.asModel() } }

    override fun getUserByEmail(email: String): Flow<User> = emptyFlow()
//        flowOf(users.first { it.email == email })
}