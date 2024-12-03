package com.example.androidcicdapp.ui.screens.calculator

import androidx.lifecycle.ViewModel
import com.example.androidcicdapp.data.calculator.Calculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculator: Calculator
) : ViewModel() {
    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result.asStateFlow()

    private var firstNumber: Double? = null
    private var operation: String? = null

    fun onNumber(number: String) {
        _result.value += number
    }

    fun onOperation(op: String) {
        firstNumber = _result.value.toDoubleOrNull()
        operation = op
        _result.value = ""
    }

    fun onEquals() {
        val secondNumber = _result.value.toDoubleOrNull()
        if (firstNumber != null && secondNumber != null && operation != null) {
            val result = when (operation) {
                "+" -> calculator.add(firstNumber!!, secondNumber)
                "-" -> calculator.subtract(firstNumber!!, secondNumber)
                "*" -> calculator.multiply(firstNumber!!, secondNumber)
                "/" -> calculator.divide(firstNumber!!, secondNumber)
                else -> return
            }
            _result.value = result.toString()
        }
    }

    fun onClear() {
        _result.value = ""
        firstNumber = null
        operation = null
    }
}