package com.example.unitconverter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.screens.PickMeasurementScreen
import org.junit.Rule
import org.junit.Test

class PickMeasurementScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pickMeasurement_isDisplayed() {
        composeTestRule.setContent {
            PickMeasurementScreen(navController = rememberNavController())
        }

        composeTestRule.onNodeWithText("Pick Measurement Type").assertIsDisplayed()
    }

    @Test
    fun temperatureButton_isDisplayed() {
        composeTestRule.setContent {
            PickMeasurementScreen(navController = rememberNavController())
        }

        composeTestRule.onNodeWithText("Temperature").assertIsDisplayed()
    }

    @Test
    fun lengthButton_isDisplayed() {
        composeTestRule.setContent {
            PickMeasurementScreen(navController = rememberNavController())
        }

        composeTestRule.onNodeWithText("Length").assertIsDisplayed()
    }

    @Test
    fun weightButton_isDisplayed() {
        composeTestRule.setContent {
            PickMeasurementScreen(navController = rememberNavController())
        }

        composeTestRule.onNodeWithText("Weight").assertIsDisplayed()
    }
}