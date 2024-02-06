package com.amarinag.randomuser.feature.users.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import com.amarinag.randomuser.core.designsystem.component.SEARCH_ICON_TEST_TAG
import com.amarinag.randomuser.core.testing.robot.BaseRobot
import com.amarinag.randomuser.core.testing.robot.ComposeTestRule
import com.amarinag.randomuser.feature.users.R

fun usersRobot(
    composeTestRule: ComposeTestRule,
    func: UsersRobot.() -> Unit
): UsersRobot {
    return UsersRobot(composeTestRule).apply(func)
}

class UsersRobot(composeTestRule: ComposeTestRule) :
    BaseRobot(composeTestRule) {
    private val loadingLabel by lazy {
        composeTestRule.activity.resources.getString(R.string.feature_users_loading)
    }

    fun isLoading() {
        composeTestRule.onNodeWithText(loadingLabel).assertExists()
    }

    fun scrollToIndex(index: Int) {
        composeTestRule.waitForIdle()
        composeTestRule.onAllNodes(hasScrollToNodeAction())
            .onFirst()
            .performScrollToIndex(index)
    }

    fun countItem(totalExpected: Int) {
        composeTestRule.onAllNodes(hasScrollToNodeAction())
            .onFirst()
            .onChildren()
            .assertCountEquals(totalExpected)
    }

    fun clickItemOnIndex(index: Int) {
        composeTestRule.onAllNodes(hasScrollToNodeAction())
            .onFirst()
            .performScrollToIndex(index - 1)
            .onChildAt(index)
            .performClick()
    }

    fun loadingRowDisplayed() {
        composeTestRule.onNodeWithText(loadingLabel).assertIsDisplayed()
    }

    fun searchbarShowing() {
        composeTestRule.onNodeWithText("Search").assertIsDisplayed()
    }

    fun searchbarHidden() {
        composeTestRule.onNodeWithText("Search").assertIsNotDisplayed()
    }

    fun clickOnSearchIcon() {
        composeTestRule.onNodeWithTag(SEARCH_ICON_TEST_TAG).performClick()
    }
}