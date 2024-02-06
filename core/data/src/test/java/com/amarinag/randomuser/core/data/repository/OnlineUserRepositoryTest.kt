package com.amarinag.randomuser.core.data.repository

import app.cash.turbine.test
import com.amarinag.randomuser.core.network.RandomUserDataSource
import com.amarinag.randomuser.core.network.model.NetworkInfo
import com.amarinag.randomuser.core.network.model.NetworkResponse
import com.amarinag.randomuser.core.testing.data.networkUserTestData
import com.amarinag.randomuser.core.testing.data.userTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class OnlineUserRepositoryTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var network: RandomUserDataSource

    private lateinit var onlineUserRepository: OnlineUserRepository

    @Before
    fun setUp() {
        onlineUserRepository = OnlineUserRepository(network)
    }

    @Test
    fun `when query is null or empty return all users`() = runTest {
        givenUsers()
        onlineUserRepository.getUsers(null).test {
            assertEquals(ANY_USERS, awaitItem())
            awaitComplete()
            assertEquals(onlineUserRepository.seed, ANY_NETWORK_RESPONSE.info.seed)
            assertEquals(onlineUserRepository.currentPage, 1)
            assertEquals(onlineUserRepository.lastQuery, "")
        }
        coVerify(exactly = 1) { network.getUsers(any(), any()) }
    }

    @Test
    fun `when query not null or empty return filtered users`() = runTest {
        givenUsers()
        givenLocalUsers()
        onlineUserRepository.getUsers(ANY_QUERY).test {
            assertEquals(FILTERED_USERS, awaitItem())
            awaitComplete()
            assertEquals(onlineUserRepository.lastQuery, ANY_QUERY)
        }
        coVerify(exactly = 0) { network.getUsers(any(), any()) }
    }

    @Test
    fun `when email return user`() = runTest {
        givenLocalUsers()
        onlineUserRepository.getUserByEmail(ANY_EMAIL).test {
            assertEquals(ANY_USER_WITH_ANY_EMAIL, awaitItem())
            awaitComplete()
        }
        coVerify(exactly = 0) { network.getUsers(any(), any()) }
    }

    private fun givenUsers() {
        coEvery { network.getUsers(any(), null) } returns ANY_NETWORK_RESPONSE
    }

    private fun givenLocalUsers() {
        onlineUserRepository.users.addAll(ANY_USERS)
    }

    private companion object {
        const val ANY_QUERY = "as"
        const val ANY_EMAIL = "jayden.andrews@example.com"
        val ANY_USERS = userTestData
        val ANY_USER_WITH_ANY_EMAIL = userTestData.first { it.email == ANY_EMAIL }
        val ANY_NETWORK_INFO = NetworkInfo(
            page = 0,
            results = 10,
            seed = "123456",
            version = "11"
        )
        val ANY_NETWORK_RESPONSE = NetworkResponse(ANY_NETWORK_INFO, networkUserTestData)
        val FILTERED_USERS = ANY_USERS.filter {
            it.email.uppercase().contains(ANY_QUERY.uppercase()) || it.name.fullname.uppercase()
                .contains(ANY_QUERY.uppercase())
        }
    }
}