package com.example.unitconverter

import com.example.unitconverter.domain.UnitConversionViewModel

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConvertUnitsTest {

    private lateinit var viewModel: UnitConversionViewModel

    // Setting up view model for testing
    @Before
    fun setUp() {
        viewModel = UnitConversionViewModel()
    }

    @Test
    fun ConvertUnits_Top_MetersToCentimeters() {
        viewModel.topUnitText = "1.0"
        viewModel.convertUnits("top", "m", "cm", "length")

        assertEquals(100.0, viewModel.bottomUnitText.toDouble(), 0.0001)
    }

    @Test
    fun ConvertUnits_Bottom_KilogramsToPounds() {
        viewModel.bottomUnitText = "5.0"
        viewModel.convertUnits("bottom", "kg", "lb", "weight")

        assertEquals(11.0231, viewModel.topUnitText.toDouble(), 0.0001)
    }

    @Test
    fun ConvertUnits_Top_KelvinToFahrenheit() {
        viewModel.topUnitText = "100"
        viewModel.convertUnits("top", "K", "F", "temperature")

        assertEquals(-279.67, viewModel.bottomUnitText.toDouble(), 0.0001)
    }



}