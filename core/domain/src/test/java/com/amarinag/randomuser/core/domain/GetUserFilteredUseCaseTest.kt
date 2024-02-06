package com.amarinag.randomuser.core.domain

import app.cash.turbine.test
import com.amarinag.randomuser.core.data.repository.UserRepository
import com.amarinag.randomuser.core.domain.GetUserFilteredUseCase.Params
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

class GetUserFilteredUseCaseTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var userRepository: UserRepository

    private lateinit var getUserFilteredUseCase: GetUserFilteredUseCase

    @Before
    fun setUp() {
        getUserFilteredUseCase = GetUserFilteredUseCase(userRepository)
    }

    @Test
    fun `when params query return user with the same email`() = runTest {
        givenUserSuccess()

        getUserFilteredUseCase(ANY_PARAMS).test {
            assertEquals(awaitItem().size, 2)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { userRepository.getUsers(ANY_QUERY) }
    }

    private fun givenUserSuccess() =
        coEvery { userRepository.getUsers(ANY_QUERY) } returns flowOf(ANY_USERS.filter {
            it.email.uppercase().contains(ANY_QUERY.uppercase()) || it.name.fullname.uppercase()
                .contains(ANY_QUERY.uppercase())
        })

    private companion object {
        const val ANY_QUERY = "as"
        val ANY_USERS = userTestData
        val ANY_PARAMS = Params(ANY_QUERY)
    }
}