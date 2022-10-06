package com.example.nickelffoxassignments_sheenu.news.data.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.nickelffoxassignments_sheenu.news.data.local.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Articles")
    fun getArticle():PagingSource<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun addArticles(news:List<Article>)

     @Query("DELETE FROM Articles")
     suspend fun deleteArticles()


     @Query("SELECT checkableTitle FROM Articles WHERE url=:url")
     suspend fun getArticleCheckableStatus(url:String):String

     @Query("UPDATE Articles SET checkableTitle=:status WHERE url=:url" )
     fun updateArticleCheckableStaus(url:String,status:String)

}