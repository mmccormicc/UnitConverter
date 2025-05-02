package com.example.unitconverter

import com.example.unitconverter.domain.UnitConversionViewModel
import com.example.unitconverter.domain.UnitConversionViewModel.LengthUnit
import com.example.unitconverter.domain.UnitConversionViewModel.TemperatureUnit
import com.example.unitconverter.domain.UnitConversionViewModel.WeightUnit

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ParseUnitsTest {

    private lateinit var viewModel: UnitConversionViewModel

    // Setting up view model for testing
    @Before
    fun setUp() {
        viewModel = UnitConversionViewModel()
    }

    @Test
    fun ParseUnits_MetersToFeet() {
        viewModel.parseUnits("m", "ft")

        assertEquals(LengthUnit.METER, viewModel.startUnits)
        assertEquals(LengthUnit.FOOT, viewModel.endUnits)
    }

    @Test
    fun ParseUnits_FahrenheitToCelsius() {
        viewModel.parseUnits("F", "C")

        assertEquals(TemperatureUnit.FAHRENHEIT, viewModel.startUnits)
        assertEquals(TemperatureUnit.CELSIUS, viewModel.endUnits)
    }

    @Test
    fun ParseUnits_KilogramsToPounds() {
        viewModel.parseUnits("kg", "lb")

        assertEquals(WeightUnit.KILOGRAM, viewModel.startUnits)
        assertEquals(WeightUnit.POUND, viewModel.endUnits)
    }


}