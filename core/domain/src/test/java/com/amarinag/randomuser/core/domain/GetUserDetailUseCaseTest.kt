package com.amarinag.randomuser.core.domain

import app.cash.turbine.test
import com.amarinag.randomuser.core.data.repository.UserRepository
import com.amarinag.randomuser.core.domain.GetUserDetailUseCase.Params
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

class GetUserDetailUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var userRepository: UserRepository

    private lateinit var getUserDetailUseCase: GetUserDetailUseCase

    @Before
    fun setUp() {
        getUserDetailUseCase = GetUserDetailUseCase(userRepository)
    }

    @Test
    fun `when params email return user with the same email`() = runTest {
        givenUserSuccess()

        getUserDetailUseCase(ANY_PARAMS).test {
            assertEquals(awaitItem().email, ANY_EMAIL)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { userRepository.getUserByEmail(ANY_EMAIL) }
    }

    private fun givenUserSuccess() =
        coEvery { userRepository.getUserByEmail(ANY_EMAIL) } returns flowOf(ANY_USERS.first { it.email == ANY_EMAIL })

    private companion object {
        const val ANY_EMAIL = "lila.wegman@example.com"
        val ANY_USERS = userTestData
        val ANY_PARAMS = Params(ANY_EMAIL)
    }
}