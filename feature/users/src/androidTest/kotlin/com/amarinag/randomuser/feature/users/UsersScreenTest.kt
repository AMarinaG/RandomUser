package com.amarinag.randomuser.feature.users

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import com.amarinag.randomuser.core.designsystem.component.SEARCH_ICON_TEST_TAG
import com.amarinag.randomuser.core.testing.data.userTestData
import org.junit.Rule
import org.junit.Test

class UsersScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val loading by lazy {
        composeTestRule.activity.resources.getString(R.string.feature_users_loading)
    }
    private val onUserClick: (String) -> Unit = {}
    private val loadingLabel by lazy {
        composeTestRule.activity.resources.getString(R.string.feature_users_loading)
    }

    @Test
    fun loading_whenScreenIsLoading_exist() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(isLoading = true))
            }
        }
        composeTestRule.onNodeWithText(loadingLabel).assertExists()
    }

    @Test
    fun userList_whenScrollDown_shownLoadingRow() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(users = ANY_USERS))
            }
        }
        composeTestRule.waitForIdle()
        composeTestRule.onAllNodes(hasScrollToNodeAction()).onFirst().performScrollToIndex(3)
            .onChildren().assertCountEquals(11)
        composeTestRule.onNodeWithText(loading).assertIsDisplayed()
    }

    @Test
    fun userList_whenClickOnSearch_showTextField() {
        composeTestRule.setContent {
            Column {
                UsersScreen(uiState = UsersState(users = ANY_USERS))
            }
        }
        composeTestRule.onNodeWithText("Search").assertIsNotDisplayed()
        composeTestRule.onNodeWithTag(SEARCH_ICON_TEST_TAG).performClick()
        composeTestRule.onNodeWithText("Search").assertIsDisplayed()
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