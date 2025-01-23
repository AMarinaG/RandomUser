package com.amarinag.randomuser.feature.users

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.amarinag.randomuser.core.model.User
import com.amarinag.randomuser.core.testing.data.userTestData
import com.amarinag.randomuser.feature.users.robot.usersRobot
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsersScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    private lateinit var onUserClick: (String) -> Unit

    @MockK(relaxed = true)
    private lateinit var onUserDelete: (User) -> Unit

    @MockK(relaxed = true)
    private lateinit var onLoadMoreUsers: () -> Unit

    @MockK(relaxed = true)
    private lateinit var onQueryFilters: (String) -> Unit

    @Test
    fun loading_whenScreenIsLoading_exist() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(isLoading = true))
            }
        }
        usersRobot(composeTestRule) {
            isLoading()
        }
    }

    @Test
    fun userList_whenScrollDown_shownLoadingRow() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(users = ANY_USERS))
            }
        }
        usersRobot(composeTestRule) {
            scrollToIndex(5)
            countItem(11)
            loadingRowDisplayed()
        }
    }

    @Test
    fun userList_whenClickOnSearch_showTextField() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(users = ANY_USERS))
            }
        }
        usersRobot(composeTestRule) {
            searchbarHidden()
            clickOnSearchIcon()
            searchbarShowing()
        }
    }

    @Test
    fun userList_whenClickOnDelete_DeleteUser() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(users = ANY_USERS))
            }
        }
        usersRobot(composeTestRule) {
            clickDeleteItemOnIndex(0)
        }
        verify { onUserDelete(ANY_USERS.first()) }
    }

    @Test
    fun userList_whenClickOnItem() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(users = ANY_USERS))
            }
        }
        usersRobot(composeTestRule) {
            clickItemOnIndex(0)
        }
        verify { onUserClick(ANY_USERS.first().email) }
    }

    @Composable
    private fun UsersScreen(uiState: UsersState) {
        UsersScreen(
            uiState = uiState,
            onUserClick = onUserClick,
            onDeleteUser = onUserDelete,
            loadMoreUsers = onLoadMoreUsers,
            queryFilter = onQueryFilters,
        )
    }

    private companion object {
        val ANY_USERS = userTestData
    }
}