package com.amarinag.randomuser.core.domain

import app.cash.turbine.test
import com.amarinag.randomuser.core.data.repository.UserRepository
import com.amarinag.randomuser.core.testing.data.userTestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class GetUsersUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var userRepository: UserRepository

    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setUp() {
        getUsersUseCase = GetUsersUseCase(userRepository)
    }

    @Test
    fun `when invokes getUsers returns all users`() = runTest {
        givenGetUsersSuccess()

        getUsersUseCase().test {
            assertEquals(awaitItem(), userTestData)
            awaitComplete()
        }
        coVerify { userRepository.getUsers() }
    }

    private fun givenGetUsersSuccess() =
        coEvery { userRepository.getUsers() } returns flowOf(userTestData)
}

