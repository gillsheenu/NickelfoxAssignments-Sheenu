package com.example.nickelffoxassignments_sheenu.news.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Articles")
    fun getArticle():PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addArticles(news:List<Article>)

     @Query("DELETE FROM Articles")
     suspend fun deleteArticles()
}