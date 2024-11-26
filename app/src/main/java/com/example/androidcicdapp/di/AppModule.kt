package com.example.androidcicdapp.di

import com.example.androidcicdapp.data.calculator.Calculator
import com.example.androidcicdapp.data.calculator.CalculatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCalculator(): Calculator = CalculatorImpl()
}