package com.amarinag.randomuser

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Test

class AppTest {
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun showUserList_whenLauchApp() {
        composeTestRule.apply {
            onNodeWithText("Contacts")
        }
    }
}