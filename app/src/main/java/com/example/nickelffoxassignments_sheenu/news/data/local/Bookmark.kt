package com.example.nickelffoxassignments_sheenu.news.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bookmark")
data class Bookmark (
    val source: String,
    val author:String?=null,
    val title:String,
    val urlToImage:String,

    @PrimaryKey(autoGenerate = false)
    val url:String)
