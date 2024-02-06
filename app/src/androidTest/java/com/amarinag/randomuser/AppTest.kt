package com.amarinag.randomuser

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import com.amarinag.randomuser.feature.users.UserListTestTag
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class AppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun showUserList_whenLaunchApp_and_navigate_to_detail_user() {
        composeTestRule.apply {
            composeTestRule.waitUntilAtLeastOneExists(hasTestTag(UserListTestTag), 10000)
            composeTestRule.onAllNodes(hasScrollToNodeAction())
                .onFirst()
                .performScrollToIndex(3)
                .onChildAt(4)
                .performClick()
            composeTestRule.waitUntilAtLeastOneExists(hasTestTag("UserInfoTestTag"), 10000)
        }
    }
}