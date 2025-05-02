package com.example.unitconverter.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UnitConversionViewModel(): ViewModel() {

    interface ConvertibleUnit

    // Holds conversion factor from Meters to Unit
    enum class LengthUnit(val toMeters: Double): ConvertibleUnit {
        METER(1.0),
        KILOMETER(1000.0),
        CENTIMETER(0.01),
        INCH(0.0254),
        FOOT(0.3048)
    }

    // Holds conversion factor from Kilograms to Unit
    enum class WeightUnit(val toKilograms: Double): ConvertibleUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592),
        OUNCE(0.0283495)
    }

    // Holds temperature enums. Conversion is done later with custom funciton.
    enum class TemperatureUnit: ConvertibleUnit {
        CELSIUS,
        FAHRENHEIT,
        KELVIN
    }

    // Top unit text box
    var topUnitText by mutableStateOf("")
    // Bottom unit text box
    var bottomUnitText by mutableStateOf("")

    // Units value starts in
    var startUnits by mutableStateOf<ConvertibleUnit?>(null)
    // Units that value should end in
    var endUnits by mutableStateOf<ConvertibleUnit?>(null)


    // Sets text field of side not interacted with to be converted units
    fun convertUnits(sideChanged: String, startUnitString: String, endUnitString: String, measurementType: String) {

        // Getting a double from the text that was changed
        // Set to null if text is not a double
        var inputDouble: Double? = when (sideChanged) {
            "top" -> topUnitText.toDoubleOrNull()
            "bottom" -> bottomUnitText.toDoubleOrNull()
            else -> null
        }

        // Setting input to 0 if it wasn't recognized as a double (Useful for empty measurement field)
        if (inputDouble == null) {
            inputDouble = 0.0
        }

        // Setting startUnits and endUnits by parsing string
        parseUnits(startUnitString, endUnitString)

        // If both units are entered
        if (startUnits != null && endUnits != null) {
            // If top side was changed
            if (sideChanged == "top") {
                // Modify bottom size
                bottomUnitText = when (measurementType) {
                    // Calling convert function for individual units
                    "length" -> convertLength(
                        inputDouble,
                        startUnits as LengthUnit,
                        endUnits as LengthUnit
                    )

                    "weight" -> convertWeight(
                        inputDouble,
                        startUnits as WeightUnit,
                        endUnits as WeightUnit
                    )

                    "temperature" -> convertTemperature(
                        inputDouble,
                        startUnits as TemperatureUnit,
                        endUnits as TemperatureUnit
                    )

                    else -> ""
                }.toString()
            // If bottom side was changed
            } else if (sideChanged == "bottom") {
                // Modify top side
                topUnitText = when (measurementType) {
                    "length" -> convertLength(
                        inputDouble,
                        startUnits as LengthUnit,
                        endUnits as LengthUnit
                    )

                    "weight" -> convertWeight(
                        inputDouble,
                        startUnits as WeightUnit,
                        endUnits as WeightUnit
                    )

                    "temperature" -> convertTemperature(
                        inputDouble,
                        startUnits as TemperatureUnit,
                        endUnits as TemperatureUnit
                    )

                    else -> ""
                }.toString()
            }
        }


    }

    // Parsing unit types from drop down menu strings
    fun parseUnits(startUnitString: String, endUnitString: String) {
        startUnits = when(startUnitString) {
            "m" -> LengthUnit.METER
            "km" -> LengthUnit.KILOMETER
            "cm" -> LengthUnit.CENTIMETER
            "in" -> LengthUnit.INCH
            "ft" -> LengthUnit.FOOT
            "kg" -> WeightUnit.KILOGRAM
            "g" -> WeightUnit.GRAM
            "lb" -> WeightUnit.POUND
            "oz" -> WeightUnit.OUNCE
            "C" -> TemperatureUnit.CELSIUS
            "F" -> TemperatureUnit.FAHRENHEIT
            "K" -> TemperatureUnit.KELVIN
            else -> null
        }

        endUnits = when(endUnitString) {
            "m" -> LengthUnit.METER
            "km" -> LengthUnit.KILOMETER
            "cm" -> LengthUnit.CENTIMETER
            "in" -> LengthUnit.INCH
            "ft" -> LengthUnit.FOOT
            "kg" -> WeightUnit.KILOGRAM
            "g" -> WeightUnit.GRAM
            "lb" -> WeightUnit.POUND
            "oz" -> WeightUnit.OUNCE
            "C" -> TemperatureUnit.CELSIUS
            "F" -> TemperatureUnit.FAHRENHEIT
            "K" -> TemperatureUnit.KELVIN
            else -> null
        }
    }

    fun convertLength(value: Double, start: LengthUnit, end: LengthUnit): Double {
        // Converting start value to meters
        val valueInMeters = value * start.toMeters
        // Converting meters to end value
        return valueInMeters / end.toMeters
    }

    fun convertWeight(value: Double, start: WeightUnit, end: WeightUnit): Double {
        // Converting start value to kilograms
        val valueInKilograms = value * start.toKilograms
        // Converting kilograms to end value
        return valueInKilograms / end.toKilograms
    }

    fun convertTemperature(value: Double, start: TemperatureUnit, end: TemperatureUnit): Double {
        // Converting start value to celsius
        val startValueInCelsius: Double = when(start) {
            // Already in celsius
            TemperatureUnit.CELSIUS -> value
            // Converting from fahrenheit to celsius
            TemperatureUnit.FAHRENHEIT -> (value - 32) * (5.0/9.0)
            // Converting from kelvin to celsius
            TemperatureUnit.KELVIN -> value - 273.15
        }

        // Converting celsius value to end units
        return when(end) {
            // Both in celsius
            TemperatureUnit.CELSIUS -> startValueInCelsius
            // Celsius to fahrenheit
            TemperatureUnit.FAHRENHEIT -> (startValueInCelsius * (9.0/5.0)) + 32
            // Celsius to kelvin
            TemperatureUnit.KELVIN -> value + 273.15
        }
    }
}