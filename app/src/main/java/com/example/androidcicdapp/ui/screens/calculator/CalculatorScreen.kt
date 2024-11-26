package com.example.androidcicdapp.ui.screens.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel(),
    modifier: Modifier
) {
    val result by viewModel.result.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = result,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("7", "8", "9", "/").forEach { symbol ->
                Button(
                    onClick = {
                        if (symbol in "0123456789") viewModel.onNumber(symbol)
                        else viewModel.onOperation(symbol)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(symbol)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("4", "5", "6", "*").forEach { symbol ->
                Button(
                    onClick = {
                        if (symbol in "0123456789") viewModel.onNumber(symbol)
                        else viewModel.onOperation(symbol)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(symbol)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("1", "2", "3", "-").forEach { symbol ->
                Button(
                    onClick = {
                        if (symbol in "0123456789") viewModel.onNumber(symbol)
                        else viewModel.onOperation(symbol)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(symbol)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("0", ".", "=", "+").forEach { symbol ->
                Button(
                    onClick = {
                        when (symbol) {
                            "=" -> viewModel.onEquals()
                            in "0123456789." -> viewModel.onNumber(symbol)
                            else -> viewModel.onOperation(symbol)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(symbol)
                }
            }
        }

        Button(
            onClick = { viewModel.onClear() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear")
        }
    }
}