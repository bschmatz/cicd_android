package com.example.androidcicdapp.calculator

import com.example.androidcicdapp.data.calculator.CalculatorImpl
import org.junit.Test
import org.junit.Assert.*

class CalculatorImplTest {
    private val calculator = CalculatorImpl()

    @Test
    fun add_isCorrect() {
        val result = calculator.add(17.0, 4.0)
        assertEquals(21.0, result, 0.0)
    }

    @Test
    fun subtract_isCorrect() {
        val result = calculator.subtract(10.0, 1.5)
        assertEquals(8.5, result, 0.0)
    }

    @Test
    fun multiply_isCorrect() {
        val result = calculator.multiply(5.0, 5.0)
        assertEquals(25.0, result, 0.0)
    }

    @Test
    fun divide_isCorrect() {
        val result = calculator.divide(99.0, 33.0)
        assertEquals(3.0, result, 0.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun divide_divideByZero_throwsException() {
        calculator.divide(1.0, 0.0)
    }
}