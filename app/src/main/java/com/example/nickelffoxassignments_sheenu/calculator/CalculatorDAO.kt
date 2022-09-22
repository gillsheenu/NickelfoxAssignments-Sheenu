package com.example.nickelffoxassignments_sheenu.calculator

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CalculatorDAO {

    @Insert
    suspend fun insertExpression(calculation:Calculations)

    @Update
    suspend fun updateExpression(calculation: Calculations)

//    @Delete
//    suspend fun deleteExpression(calculation: Calculations)


    @Query("SELECT * FROM calculation")
    fun getCalculation(): LiveData<List<Calculations>>
}