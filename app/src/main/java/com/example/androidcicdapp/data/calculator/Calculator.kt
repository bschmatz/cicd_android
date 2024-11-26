package com.example.androidcicdapp.data.calculator

interface Calculator {
    fun add(a: Double, b: Double): Double
    fun subtract(a: Double, b: Double): Double
    fun multiply(a: Double, b: Double): Double
    fun divide(a: Double, b: Double): Double
}