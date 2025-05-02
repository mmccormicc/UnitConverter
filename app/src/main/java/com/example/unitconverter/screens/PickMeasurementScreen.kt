package com.example.unitconverter.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun PickMeasurementScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Pick Measurement Type",
            style = TextStyle(fontSize = 32.sp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("unit_conversion_screen"+"/temperature") }) {
            Text(
                text = "Temperature",
                style = TextStyle(fontSize = 32.sp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("unit_conversion_screen"+"/length") }) {
            Text(
                text = "Length",
                style = TextStyle(fontSize = 32.sp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("unit_conversion_screen"+"/weight") }) {
            Text(
                text = "Weight",
                style = TextStyle(fontSize = 32.sp)
            )
        }
    }
}
