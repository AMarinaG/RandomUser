package com.amarinag.randomuser.feature.users

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.amarinag.randomuser.core.testing.data.userTestData
import com.amarinag.randomuser.feature.users.robot.usersRobot
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class UsersScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @MockK(relaxed = true)
    private val onUserClick: (String) -> Unit = {}

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
            scrollToIndex(3)
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

    @Composable
    private fun UsersScreen(uiState: UsersState) {
        UsersScreen(
            uiState = uiState,
            onUserClick = onUserClick,
            loadMoreUsers = { },
            queryFilter = {}
        )
    }

    private companion object {
        val ANY_USERS = userTestData
    }
}