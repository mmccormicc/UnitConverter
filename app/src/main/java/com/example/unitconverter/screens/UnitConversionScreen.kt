package com.example.unitconverter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.unitconverter.domain.UnitConversionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConversionScreen(navController: NavHostController, measurementType: String) {

    val viewModel: UnitConversionViewModel = viewModel()

    // Setting dropdown options based on measurement type
    val dropdownOptions = when(measurementType) {
        "length" -> listOf("m", "km", "cm", "in", "ft")
        "weight" -> listOf("kg", "g", "lb", "oz")
        "temperature" -> listOf("C", "F", "K")
        else -> listOf("error")
    }

    // Top dropdown menu variables
    var topExpanded by remember { mutableStateOf(false) }
    var topSelectedUnit by remember { mutableStateOf("Choose Units") }

    // Bottom dropdown menu variables
    var bottomExpanded by remember { mutableStateOf(false) }
    var bottomSelectedUnit by remember { mutableStateOf("Choose Units") }

    var valueEntered by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top measurement entry text field
        OutlinedTextField(
            value = viewModel.topUnitText,
            onValueChange = {
                viewModel.topUnitText = it
                viewModel.convertUnits("top", topSelectedUnit, bottomSelectedUnit, measurementType)
                valueEntered = true
            },
            label = { Text("Measurement 1") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Top half unit selection
        ExposedDropdownMenuBox(
            expanded = topExpanded,
            onExpandedChange = { topExpanded = !topExpanded },
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
        ) {
            TextField(
                readOnly = true,
                value = topSelectedUnit,
                onValueChange = { },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = topExpanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            ExposedDropdownMenu(
                expanded = topExpanded,
                onDismissRequest = { topExpanded = false }
            ) {
                dropdownOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {Text(text = selectionOption, style = TextStyle(
                            fontSize = 18.sp))},
                        onClick = {
                            topSelectedUnit = selectionOption

                            topExpanded = false

                            if (valueEntered) {
                                viewModel.convertUnits(
                                    "top",
                                    topSelectedUnit,
                                    bottomSelectedUnit,
                                    measurementType
                                )
                            }
                        }
                    )
                }
            }
        }


        Text(
            text = "=",
            style = TextStyle(fontSize = 64.sp)
        )

        // Bottom measurement entry text field
        OutlinedTextField(
            value = viewModel.bottomUnitText,
            onValueChange = {
                viewModel.bottomUnitText = it
                viewModel.convertUnits("bottom", topSelectedUnit, bottomSelectedUnit, measurementType)
                valueEntered = true
            },
            label = { Text("Measurement 2") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Bottom half unit selection
        ExposedDropdownMenuBox(
            expanded = bottomExpanded,
            onExpandedChange = { bottomExpanded = !bottomExpanded},
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
        ) {
            TextField(
                readOnly = true,
                value = bottomSelectedUnit,
                onValueChange = { },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = bottomExpanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                textStyle = TextStyle(fontSize = 18.sp)
            )
            ExposedDropdownMenu(
                expanded = bottomExpanded,
                onDismissRequest = { bottomExpanded = false }
            ) {
                dropdownOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = {Text(text = selectionOption, style = TextStyle(
                            fontSize = 18.sp))},
                        onClick = {
                            bottomSelectedUnit = selectionOption

                            bottomExpanded = false

                            if (valueEntered) {
                                viewModel.convertUnits(
                                    "bottom",
                                    topSelectedUnit,
                                    bottomSelectedUnit,
                                    measurementType
                                )
                            }
                        }
                    )
                }
            }
        }

        // Spacer between conversion and change measurement button
        Spacer(modifier = Modifier.height(64.dp))

        // Change measurement button
        Button(onClick = { navController.navigate("pick_measurement_screen")}) {
            Text(
                text = "Change Measurement",
                style = TextStyle(fontSize = 24.sp)
            )
        }
    }
}
