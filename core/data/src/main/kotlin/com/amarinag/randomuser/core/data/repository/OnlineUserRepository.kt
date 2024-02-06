package com.amarinag.randomuser.core.data.repository

import androidx.annotation.VisibleForTesting
import com.amarinag.randomuser.core.data.model.asDomain
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.network.RandomUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

private const val INITIAL_PAGE = 0

@Singleton
class OnlineUserRepository @Inject constructor(
    private val network: RandomUserDataSource
) : UserRepository {
    @VisibleForTesting
    var seed: String? = null
    @VisibleForTesting
    var currentPage: Int = INITIAL_PAGE
    @VisibleForTesting
    var lastQuery: String? = ""
    @VisibleForTesting
    val users: MutableList<User> = mutableListOf()
    override fun getUsers(query: String?): Flow<List<User>> = flow {
        if (query.isNullOrEmpty() || query == lastQuery) {
            currentPage = currentPage.plus(1)
            val response = network.getUsers(currentPage, seed)
            seed = response.info.seed
            users.addAll(response.users.asDomain())
            emit(users)
        } else {
            lastQuery = query
            emit(users.filter {
                it.email.uppercase().contains(query.uppercase()) || it.name.fullname.uppercase()
                    .contains(query.uppercase())
            })
        }
    }

    override fun getUserByEmail(email: String): Flow<User> =
        flowOf(users.first { it.email == email })
}