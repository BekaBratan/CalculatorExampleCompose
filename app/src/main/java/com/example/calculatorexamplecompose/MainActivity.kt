package com.example.calculatorexamplecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorExampleApp()
        }
    }
}

@Composable
fun CalculatorExampleApp(modifier: Modifier = Modifier){
    val firstValue = remember { mutableStateOf("") }
    val secondValue = remember { mutableStateOf("") }
    val result = remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (result.value.isNotEmpty())
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                text = "Result:\n" +
                    if (result.value.toDoubleOrNull() != null && result.value.toDouble() % 1 == 0.0){
                        result.value.toDouble().toInt().toString()
                    } else {
                        result.value
                    }
            )

        OutlinedTextField(
            value = firstValue.value,
            onValueChange = { firstValue.value = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Enter first value") }
        )
        Spacer(modifier.size(16.dp))
        OutlinedTextField(
            value = secondValue.value,
            onValueChange = { secondValue.value = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            label = { Text("Enter second value") }
        )

        Spacer(modifier.size(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (firstValue.value.toDoubleOrNull() != null && secondValue.value.toDoubleOrNull() != null)
                            result.value =
                                (firstValue.value.toDouble() + secondValue.value.toDouble()).toString()
                        else
                            result.value = "Type Number"
                    }
                ) {
                    Text("+")
                }
                Button(
                    onClick = {
                        if (firstValue.value.toDoubleOrNull() != null && secondValue.value.toDoubleOrNull() != null)
                            result.value =
                                (firstValue.value.toDouble() - secondValue.value.toDouble()).toString()
                        else
                            result.value = "Type Number"
                    }
                ) {
                    Text("-")
                }
                Button(
                    onClick = {
                        if (firstValue.value.toDoubleOrNull() != null && secondValue.value.toDoubleOrNull() != null)
                            result.value =
                                (firstValue.value.toDouble() * secondValue.value.toDouble()).toString()
                        else
                            result.value = "Type Number"
                    }
                ) {
                    Text("*")
                }
                Button(
                    onClick = {
                        if (firstValue.value.toDoubleOrNull() != null && secondValue.value.toDoubleOrNull() != null)
                            result.value =
                                if (secondValue.value.toInt() != 0) {
                                    (firstValue.value.toDouble() / secondValue.value.toDouble()).toString()
                                } else {
                                    "Can't be divided to 0"
                                }
                        else
                            result.value = "Type Number"
                    }
                ) {
                    Text("/")
                }
            }
            Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Button(
                    onClick = {
                        if (firstValue.value.toDoubleOrNull() != null && secondValue.value.toDoubleOrNull() != null)
                            result.value = (firstValue.value.toDouble() * (secondValue.value.toDouble() / 100)).toString()
                        else
                            result.value = "Type Number"
                    }
                ) {
                    Text("%")
                }
                Button(
                    onClick = {
                        if (firstValue.value.toDoubleOrNull() != null && secondValue.value.toDoubleOrNull() != null)
                            result.value = firstValue.value.toDouble().pow(secondValue.value.toDouble()).toString()
                        else
                            result.value = "Type Number"
                    }
                ) {
                    Text("^")
                }
                Button(
                    onClick = {
                        firstValue.value = ""
                        secondValue.value = ""
                        result.value = ""
                    }
                ) {
                    Text("C")
                }
            }
        }
    }
}