package com.example.nickelffoxassignments_sheenu.stopwatch.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.work.*
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchWorker
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.view.StopWatchFragment


class NotificationIntentService: BroadcastReceiver() {
//    private lateinit var stopWatchRequest:OneTimeWorkRequest
    override fun onReceive(p0: Context?, p1: Intent) {
        Log.d("TAGINTE", "onReceive:$p1 ")

        var updatedTime= p1.getIntExtra("INPUTVALUE",30)
        when(p1.action){
           "play" -> {

//
               if (StopWatchFragment.isCancelled==false) {
                   StopWatchFragment.isCancelled=true

                  var stopWatchRequest = OneTimeWorkRequestBuilder<StopWatchWorker>()
                       .setInputData(workDataOf("INPUT" to StopWatchFragment.inputValue))
                       .build()

                   p0?.let {
                       WorkManager.getInstance(it)
                           .enqueueUniqueWork(
                               "FirstWork",
                               ExistingWorkPolicy.REPLACE,
                               stopWatchRequest
                           )
                   }
               }
           }

            "pause"->{

                StopWatchFragment.isCancelled=false
                p0?.let { WorkManager.getInstance(it).cancelAllWork()
                }
            }
            "cancel"->{
                if(StopWatchFragment.isCancelled==false){
                    var intent=Intent(p0,StopWatchService::class.java)
                    p0?.stopService(intent)
                    StopWatchFragment.isCancelled=true
                    StopWatchWorker.workerLiveData.postValue(0)
                    StopWatchFragment.inputValue =0
                }else{
                    p0?.let {
                        StopWatchFragment.isCancelled=true
                        WorkManager.getInstance(it).cancelAllWork()


                    }
                }



            }
        }
    }
}