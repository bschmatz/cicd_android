package com.example.androidcicdapp.data.calculator

class CalculatorImpl : Calculator {
    override fun add(a: Double, b: Double) = a + b
    override fun subtract(a: Double, b: Double) = a - b
    override fun multiply(a: Double, b: Double) = a * b
    override fun divide(a: Double, b: Double): Double {
        if (b == 0.0) throw IllegalArgumentException("Cannot divide by zero")
        return a / b
    }
}