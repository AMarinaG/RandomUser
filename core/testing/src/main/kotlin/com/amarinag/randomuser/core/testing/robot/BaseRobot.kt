package com.amarinag.randomuser.core.testing.robot

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

typealias ComposeTestRule = AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>
open class BaseRobot(val composeTestRule: ComposeTestRule)