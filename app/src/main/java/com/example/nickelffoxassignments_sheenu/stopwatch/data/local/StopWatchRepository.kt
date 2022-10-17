package com.example.nickelffoxassignments_sheenu.stopwatch.data.local

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.example.nickelffoxassignments_sheenu.news.db.NewsDatabase
import com.example.nickelffoxassignments_sheenu.stopwatch.data.dao.StopWatchLapItems
import com.example.nickelffoxassignments_sheenu.stopwatch.utils.StopWatchService
import com.example.nickelffoxassignments_sheenu.stopwatch.utils.TimerService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StopWatchRepository @Inject constructor(@ApplicationContext var context: Context, var newsDatabase: NewsDatabase) {

    var id =0
    companion object{
        var workerLiveData= MutableLiveData<Int>()
        var secondsValue:Int=0
    }



    private var updatedTime= object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent) {
            secondsValue=p1.getIntExtra("TIME_EXTRA",30)
            workerLiveData.postValue(secondsValue)

        }
    }

    init {
        context.registerReceiver(updatedTime, IntentFilter("timerUpdated"))
    }





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


     fun createRequest(){

         val stopWatchWorkRequest= OneTimeWorkRequestBuilder<StopWatchWorker>()
             .setInputData(workDataOf("INPUT" to secondsValue))
             .build()
         WorkManager.getInstance(context)
             .enqueueUniqueWork("FirstWork", ExistingWorkPolicy.REPLACE, stopWatchWorkRequest)
     }

    fun pauseRequest(){
        WorkManager.getInstance(context).cancelUniqueWork("FirstWork")

        val intent=Intent(context, TimerService::class.java)
        context.stopService(intent)
    }

    fun stopStopWatchService(){
        val intent=Intent(context,StopWatchService::class.java)
        context.stopService(intent)
    }

    fun stopRequest(){

        WorkManager.getInstance(context).cancelAllWork()


        val intent=Intent(context, TimerService::class.java)
        context.stopService(intent)


        val notificationServiceIntent=Intent(context,StopWatchService::class.java)
        context.stopService(notificationServiceIntent)
        workerLiveData.postValue(0)
        secondsValue=0

    }

}