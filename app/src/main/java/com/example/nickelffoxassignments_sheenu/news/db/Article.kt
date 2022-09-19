package com.example.nickelffoxassignments_sheenu.news.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Articles")
data class Article(val source: Source,
                   val author:String?=null,
                   val title:String,

                   @PrimaryKey(autoGenerate = false)
                    val url:String,

                   val urlToImage:String)



