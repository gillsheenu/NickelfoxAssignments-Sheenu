package com.example.nickelffoxassignments_sheenu.stopwatch.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nickelffoxassignments_sheenu.calculator.Calculations
import com.example.nickelffoxassignments_sheenu.news.db.Bookmark


@Dao
interface LapItemsDao {

    @Insert
    suspend fun insertLapItems(lapItems:StopWatchLapItems)


    @Query("DELETE FROM LapItems")
    suspend fun deleteLapItems()


    @Query("SELECT * FROM LapItems")
    fun getLapItems(): LiveData<List<StopWatchLapItems>>

    @Query("SELECT max(idSeq) FROM LapItems")
    suspend fun getMaxId():Int

    @Query("UPDATE LapItems SET id=0")
    suspend fun updateId()
}