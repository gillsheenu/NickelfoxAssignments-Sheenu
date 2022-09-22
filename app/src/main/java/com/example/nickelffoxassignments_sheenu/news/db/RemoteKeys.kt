package com.example.nickelffoxassignments_sheenu.news.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeys")
data class RemoteKeys(

    @PrimaryKey(autoGenerate = false)
    val url:String,
    val prevKey:Int?,
    val nexKey:Int?


)
