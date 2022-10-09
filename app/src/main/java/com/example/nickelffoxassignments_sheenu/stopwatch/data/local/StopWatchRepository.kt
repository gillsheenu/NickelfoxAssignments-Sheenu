package com.example.nickelffoxassignments_sheenu.stopwatch.data.local

import com.example.nickelffoxassignments_sheenu.news.db.NewsDatabase
import com.example.nickelffoxassignments_sheenu.stopwatch.data.dao.StopWatchLapItems
import javax.inject.Inject

class StopWatchRepository @Inject constructor(var newsDatabase: NewsDatabase) {

    var id =0

    suspend fun insertStopWatchLapitems(lapItems: String,isReset:Boolean){
        if(isReset){
            id=1
        }else{
            id=getId()
            id++
        }
        newsDatabase.getLapItemsDao().insertLapItems(StopWatchLapItems(0,id,lapItems))


    }
    suspend fun deleteLapItems(){
        newsDatabase.getLapItemsDao().deleteLapItems()
        newsDatabase.getLapItemsDao().updateId()

    }
    private suspend fun getId():Int{

        return  newsDatabase.getLapItemsDao().getMaxId()
    }
}