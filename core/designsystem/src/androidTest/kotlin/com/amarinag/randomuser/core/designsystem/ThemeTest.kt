package com.amarinag.randomuser.core.designsystem

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import com.amarinag.randomuser.core.designsystem.theme.DarkColorScheme
import com.amarinag.randomuser.core.designsystem.theme.LightColorScheme
import com.amarinag.randomuser.core.designsystem.theme.RandomUserTheme
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class ThemeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun darkThemeFalse_dynamicColorFalse() {
        composeTestRule.setContent {
            RandomUserTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                val colorScheme = LightColorScheme
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
            }
        }
    }
    @Test
    fun darkThemeTrue_dynamicColorFalse() {
        composeTestRule.setContent {
            RandomUserTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
                val colorScheme = DarkColorScheme
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
            }
        }
    }
    @Test
    fun darkThemeFalse_dynamicColorTrue() {
        composeTestRule.setContent {
            RandomUserTheme(
                darkTheme = false,
                dynamicColor = true
            ) {
                val colorScheme = dynamicLightColorSchemeWithFallback()
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
            }
        }
    }
    @Test
    fun darkThemeTrue_dynamicColorTrue() {
        composeTestRule.setContent {
            RandomUserTheme(
                darkTheme = true,
                dynamicColor = true
            ) {
                val colorScheme = dynamicDarkColorSchemeWithFallback()
                assertColorSchemesEqual(colorScheme, MaterialTheme.colorScheme)
            }
        }
    }

    @Composable
    private fun dynamicLightColorSchemeWithFallback(): ColorScheme = when {
        VERSION.SDK_INT >= VERSION_CODES.S -> dynamicLightColorScheme(LocalContext.current)
        else -> LightColorScheme
    }
    @Composable
    private fun dynamicDarkColorSchemeWithFallback(): ColorScheme = when {
        VERSION.SDK_INT >= VERSION_CODES.S -> dynamicDarkColorScheme(LocalContext.current)
        else -> DarkColorScheme
    }
    private fun assertColorSchemesEqual(
        expectedColorScheme: ColorScheme,
        actualColorScheme: ColorScheme,
    ) {
        assertEquals(expectedColorScheme.primary, actualColorScheme.primary)
        assertEquals(expectedColorScheme.onPrimary, actualColorScheme.onPrimary)
        assertEquals(expectedColorScheme.primaryContainer, actualColorScheme.primaryContainer)
        assertEquals(expectedColorScheme.onPrimaryContainer, actualColorScheme.onPrimaryContainer)
        assertEquals(expectedColorScheme.secondary, actualColorScheme.secondary)
        assertEquals(expectedColorScheme.onSecondary, actualColorScheme.onSecondary)
        assertEquals(expectedColorScheme.secondaryContainer, actualColorScheme.secondaryContainer)
        assertEquals(
            expectedColorScheme.onSecondaryContainer,
            actualColorScheme.onSecondaryContainer,
        )
        assertEquals(expectedColorScheme.tertiary, actualColorScheme.tertiary)
        assertEquals(expectedColorScheme.onTertiary, actualColorScheme.onTertiary)
        assertEquals(expectedColorScheme.tertiaryContainer, actualColorScheme.tertiaryContainer)
        assertEquals(expectedColorScheme.onTertiaryContainer, actualColorScheme.onTertiaryContainer)
        assertEquals(expectedColorScheme.error, actualColorScheme.error)
        assertEquals(expectedColorScheme.onError, actualColorScheme.onError)
        assertEquals(expectedColorScheme.errorContainer, actualColorScheme.errorContainer)
        assertEquals(expectedColorScheme.onErrorContainer, actualColorScheme.onErrorContainer)
        assertEquals(expectedColorScheme.background, actualColorScheme.background)
        assertEquals(expectedColorScheme.onBackground, actualColorScheme.onBackground)
        assertEquals(expectedColorScheme.surface, actualColorScheme.surface)
        assertEquals(expectedColorScheme.onSurface, actualColorScheme.onSurface)
        assertEquals(expectedColorScheme.surfaceVariant, actualColorScheme.surfaceVariant)
        assertEquals(expectedColorScheme.onSurfaceVariant, actualColorScheme.onSurfaceVariant)
        assertEquals(expectedColorScheme.inverseSurface, actualColorScheme.inverseSurface)
        assertEquals(expectedColorScheme.inverseOnSurface, actualColorScheme.inverseOnSurface)
        assertEquals(expectedColorScheme.outline, actualColorScheme.outline)
    }

}