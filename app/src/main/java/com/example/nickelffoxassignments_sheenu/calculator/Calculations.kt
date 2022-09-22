package com.example.nickelffoxassignments_sheenu.calculator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calculation")
data class Calculations(

    @PrimaryKey(autoGenerate = true)
    val id :Int=0,
    val expression:String,
    val result: String
)
