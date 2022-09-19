package com.example.nickelffoxassignments_sheenu.calculator

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Calculations::class], version = 1)
abstract class CalculationDatabase:RoomDatabase() {
    abstract fun getCalculatorDAO():CalculatorDAO
}