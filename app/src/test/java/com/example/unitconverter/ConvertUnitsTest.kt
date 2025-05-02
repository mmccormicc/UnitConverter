package com.example.unitconverter

import com.example.unitconverter.domain.UnitConversionViewModel
import com.example.unitconverter.domain.UnitConversionViewModel.LengthUnit
import com.example.unitconverter.domain.UnitConversionViewModel.TemperatureUnit
import com.example.unitconverter.domain.UnitConversionViewModel.WeightUnit

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

    ///////////////
    // LENGTH TESTS
    ///////////////

    @Test
    fun ConvertLength_MetersToKilometers() {
        val result = viewModel.convertLength(1000.0, LengthUnit.METER, LengthUnit.KILOMETER)
        // Asserting equals with a tolerance of 0.0001
        assertEquals(1.0, result, 0.0001)
    }

    @Test
    fun ConvertLength_InchesToCentimeters() {
        val result = viewModel.convertLength(1.0, LengthUnit.INCH, LengthUnit.CENTIMETER)
        assertEquals(2.54, result, 0.0001)
    }

    @Test
    fun ConvertLength_FeetToMeters() {
        val result = viewModel.convertLength(3.0, LengthUnit.FOOT, LengthUnit.METER)
        assertEquals(0.9144, result, 0.0001)
    }

    @Test
    fun ConvertLength_MetersToInches() {
        val result = viewModel.convertLength(1.0, LengthUnit.METER, LengthUnit.INCH)
        assertEquals(39.3701, result, 0.0001)
    }

    ///////////////
    // WEIGHT TESTS
    ///////////////

    @Test
    fun ConvertWeight_GramsToKilograms() {
        val result = viewModel.convertWeight(1000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM)
        assertEquals(1.0, result, 0.0001)
    }

    @Test
    fun ConvertWeight_KilogramsToPounds() {
        val result = viewModel.convertWeight(1.0, WeightUnit.KILOGRAM, WeightUnit.POUND)
        assertEquals(2.20462, result, 0.0001) // 1 kg ≈ 2.20462 lbs
    }

    @Test
    fun ConvertWeight_PoundsToOunces() {
        val result = viewModel.convertWeight(1.0, WeightUnit.POUND, WeightUnit.OUNCE)
        assertEquals(16.0, result, 0.1) // 1 lb ≈ 16 oz
    }

    @Test
    fun ConvertWeight_OuncesToGrams() {
        val result = viewModel.convertWeight(1.0, WeightUnit.OUNCE, WeightUnit.GRAM)
        assertEquals(28.3495, result, 0.0001)
    }

    ////////////////////
    // TEMPERATURE TESTS
    ////////////////////

    @Test
    fun ConvertTemperature_CelsiusToFahrenheit() {
        val result = viewModel.convertTemperature(0.0, TemperatureUnit.CELSIUS, TemperatureUnit.FAHRENHEIT)
        assertEquals(32.0, result, 0.0001)
    }

    @Test
    fun ConvertTemperature_FahrenheitToCelsius() {
        val result = viewModel.convertTemperature(212.0, TemperatureUnit.FAHRENHEIT, TemperatureUnit.CELSIUS)
        assertEquals(100.0, result, 0.0001)
    }

    @Test
    fun ConvertTemperature_CelsiusToKelvin() {
        val result = viewModel.convertTemperature(25.0, TemperatureUnit.CELSIUS, TemperatureUnit.KELVIN)
        assertEquals(298.15, result, 0.0001)
    }

    @Test
    fun ConvertTemperature_KelvinToFahrenheit() {
        val result = viewModel.convertTemperature(273.15, TemperatureUnit.KELVIN, TemperatureUnit.FAHRENHEIT)
        assertEquals(32.0, result, 0.0001)
    }
}