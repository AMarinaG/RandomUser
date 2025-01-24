package com.amarinag.randomuser.core.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amarinag.randomuser.core.data.model.asDomain
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.network.RandomUserDataSource

private const val INITIAL_PAGE = 1

class UserPagingSource(
    private val network: RandomUserDataSource,
    private val query: String?,
    private var seed: String? = null
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: INITIAL_PAGE
        val query = query
        return try {
            Log.d("AMG", "Hacemos peticion-> $query")
            Log.d("AMG", "Hacemos position-> $page")
            val response = network.getUsers(page, seed = seed, results = params.loadSize)
            seed = response.info.seed
            val users = response.users.asDomain()
            LoadResult.Page(
                data = users,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? = state.anchorPosition
}