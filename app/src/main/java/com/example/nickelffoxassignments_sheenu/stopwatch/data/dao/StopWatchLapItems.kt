package com.example.nickelffoxassignments_sheenu.stopwatch.data.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LapItems")
data class StopWatchLapItems(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val idSeq:Int=1,

    val time:String)
