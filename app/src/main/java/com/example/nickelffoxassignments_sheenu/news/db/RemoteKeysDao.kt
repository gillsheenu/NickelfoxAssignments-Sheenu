package com.example.nickelffoxassignments_sheenu.news.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM RemoteKeys WHERE url =:url")
    suspend fun getRemoteKeys(url:String): RemoteKeys


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys: List<RemoteKeys>)

    @Query("DELETE FROM Remotekeys")
    suspend fun deleteAllRemoteKeys()
}