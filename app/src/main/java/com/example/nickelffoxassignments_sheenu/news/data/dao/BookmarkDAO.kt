package com.example.nickelffoxassignments_sheenu.news.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nickelffoxassignments_sheenu.news.data.local.Bookmark

@Dao
interface BookmarkDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertArticles(bookmark: Bookmark)

    @Delete
    fun deleteArticles(bookmark: Bookmark)

    @Query("SELECT * FROM Bookmark")
    fun getArticles():LiveData<List<Bookmark>>
}