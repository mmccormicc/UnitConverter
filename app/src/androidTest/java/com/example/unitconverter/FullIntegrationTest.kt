package com.example.unitconverter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FullIntegrationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>() // Or your actual launcher activity

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun fullIntegration_temperatureConversionWorks() {
        // Assuring that pick measurement screen is showing
        composeTestRule.onNodeWithText("Pick Measurement Type").assertIsDisplayed()

        // Selecting Temperature
        composeTestRule.onNodeWithText("Temperature").assertIsDisplayed().performClick()

        // Selecting Celsius for top unit
        composeTestRule.onAllNodesWithText("Choose Units")[0].assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("C").assertIsDisplayed().performClick()

        // Selecting Fahrenheit for bottom unit
        composeTestRule.onNodeWithText("Choose Units").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("F").assertIsDisplayed().performClick()

        // Enter 0 for Celsius
        composeTestRule.onNodeWithText("Measurement 1").assertIsDisplayed().performTextInput("0.0")
        composeTestRule.onNodeWithText("0.0").assertIsDisplayed()

        // Confirm that 32.0 shows up for Fahrenheit
        composeTestRule.onNodeWithText("32.0").assertIsDisplayed()

        // Testing that change measurement button works
        composeTestRule.onNodeWithText("Change Measurement").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Pick Measurement Type").assertIsDisplayed()
    }

    @Test
    fun fullIntegration_weightConversionWorks() {
        // Assuring that pick measurement screen is showing
        composeTestRule.onNodeWithText("Pick Measurement Type").assertIsDisplayed()

        // Selecting Weight
        composeTestRule.onNodeWithText("Weight").assertIsDisplayed().performClick()

        // Selecting kilograms for top unit
        composeTestRule.onAllNodesWithText("Choose Units")[0].assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("kg").assertIsDisplayed().performClick()

        // Selecting grams for bottom unit
        composeTestRule.onNodeWithText("Choose Units").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("g").assertIsDisplayed().performClick()

        // Enter 1.0 for kilograms
        composeTestRule.onNodeWithText("Measurement 1").assertIsDisplayed().performTextInput("1.0")
        composeTestRule.onNodeWithText("1.0").assertIsDisplayed()

        // Confirm that 1000.0 shows up for grams
        composeTestRule.onNodeWithText("1000.0").assertIsDisplayed()

        // Testing that change measurement button works
        composeTestRule.onNodeWithText("Change Measurement").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText("Pick Measurement Type").assertIsDisplayed()
    }
}