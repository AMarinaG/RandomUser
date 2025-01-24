package com.amarinag.randomuser.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.amarinag.core.database.dao.UserDao
import com.amarinag.core.database.model.UserEntity
import com.amarinag.core.datastore.RandomUserPreferenceDataSource
import com.amarinag.randomuser.core.data.model.asEntity
import com.amarinag.randomuser.core.network.RandomUserDataSource
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val query: String? = null,
    private val seed: String? = null,
    private val network: RandomUserDataSource,
    private val dataStore: RandomUserPreferenceDataSource,
    private val userDao: UserDao
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        if (!query.isNullOrBlank()) return MediatorResult.Success(true)
        val page = when (loadType) {
            LoadType.REFRESH -> {
                if (dataStore.savedPage.first() > 1) return MediatorResult.Success(false)
                dataStore.resetPage()
                dataStore.savedPage.first()
            }

            LoadType.PREPEND -> return MediatorResult.Success(true)
            LoadType.APPEND -> {
                dataStore.incrementPage()
                dataStore.savedPage.first()
            }

        }

        try {
            val apiResponse = network.getUsers(page, seed, state.config.pageSize)
            if (loadType == LoadType.REFRESH) {
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