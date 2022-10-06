package com.example.nickelffoxassignments_sheenu.news.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Articles")
data class Article(
    val source: Source,
    val author:String?=null,
    val title:String,

    var checkbale:Boolean=false,
    var checkableTitle:String="Bookmark",
    @PrimaryKey(autoGenerate = false)
    val url:String,

    val urlToImage:String)



