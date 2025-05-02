package com.example.unitconverter

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.unitconverter.screens.UnitConversionScreen
import org.junit.Rule
import org.junit.Test

class UnitConversionScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultElements_areDisplayed() {
        composeTestRule.setContent {
            UnitConversionScreen(
                navController = rememberNavController(),
                // Choosing temperature for example argument
                measurementType = "temperature"
            )
        }

        // Check top and bottom text fields
        composeTestRule.onNodeWithText("Measurement 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Measurement 2").assertIsDisplayed()

        // Check equals sign
        composeTestRule.onNodeWithText("=").assertIsDisplayed()

        // Check change measurement button
        composeTestRule.onNodeWithText("Change Measurement").assertIsDisplayed()
    }

    @Test
    fun dropdownOptions_areDisplayed() {
        composeTestRule.setContent {
            UnitConversionScreen(
                navController = rememberNavController(),
                measurementType = "temperature"
            )
        }

        // Tap on the top dropdown menu
        composeTestRule
            .onAllNodesWithText("Choose Units")[0]
            .performClick()

        // Check if correct values displayed
        composeTestRule.onNodeWithText("C").assertIsDisplayed()
        composeTestRule.onNodeWithText("K").assertIsDisplayed()
        composeTestRule.onNodeWithText("F").assertIsDisplayed()
    }
}