package com.amarinag.randomuser.feature.userdetail

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.amarinag.randomuser.core.testing.data.userTestData
import org.junit.Rule
import org.junit.Test

class UserDetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val loadingLabel by lazy {
        composeTestRule.activity.resources.getString(R.string.feature_userdetail_loading)
    }
    private val errorLabel by lazy {
        composeTestRule.activity.resources.getString(R.string.feature_userdetail_error)
    }

    @Test
    fun loading_whenScreenIsLoading_exists() {
        composeTestRule.setContent {
            Column {
                UserDetailScreen(uiState = UserDetailUiState.Loading)
            }
        }
        composeTestRule.onNodeWithText(loadingLabel).assertExists()
    }

    @Test
    fun whenHaveUserData_showUserInfo() {
        composeTestRule.setContent {
            Column {
                UserDetailScreen(uiState = UserDetailUiState.Success(ANY_USER))
            }
        }
        composeTestRule.onAllNodes(hasText(ANY_USER.name.fullname)).assertCountEquals(2)
        composeTestRule.onNodeWithText(ANY_USER.email).assertExists()
        composeTestRule.onNodeWithText(ANY_USER.gender).assertExists()
        composeTestRule.onNodeWithText(ANY_USER.phone).assertExists()
    }

    @Test
    fun whenError_showError() {
        composeTestRule.setContent {
            Column {
                UserDetailScreen(uiState = UserDetailUiState.Error)
            }
        }
        composeTestRule.onNodeWithText(errorLabel).assertExists()
    }

    private companion object {
        val ANY_USER = userTestData.first()
    }
}