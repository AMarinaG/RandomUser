package com.amarinag.randomuser.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.amarinag.core.database.dao.UserDao
import com.amarinag.core.database.model.UserEntity
import com.amarinag.randomuser.core.data.model.asEntity
import com.amarinag.randomuser.core.network.RandomUserDataSource

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val query: String? = null,
    private val seed: String? = null,
    private val network: RandomUserDataSource,
    private val userDao: UserDao
) : RemoteMediator<Int, UserEntity>() {
    private var page = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        if(!query.isNullOrBlank()) return MediatorResult.Success(true)
        when (loadType) {
            LoadType.REFRESH -> page
            LoadType.PREPEND -> page += 1
            LoadType.APPEND -> {
                page += 1
                return MediatorResult.Success(endOfPaginationReached = false)
            }

        }

        try {
            val apiResponse = network.getUsers(page, seed, state.config.pageSize)
            if (loadType == LoadType.REFRESH) {
                // delete page from datastorage
                userDao.clearUsers()
            }
            // save last page networking
            userDao.insertUsers(apiResponse.users.asEntity())

            return MediatorResult.Success(endOfPaginationReached = false)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }
}