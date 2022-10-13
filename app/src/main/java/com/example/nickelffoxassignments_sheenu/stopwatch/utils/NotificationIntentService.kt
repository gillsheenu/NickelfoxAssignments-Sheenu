package com.example.nickelffoxassignments_sheenu.stopwatch.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.*
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchWorker
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.view.StopWatchFragment


class NotificationIntentService: BroadcastReceiver() {
    private lateinit var stopWatchRequest:OneTimeWorkRequest
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("TAGINTE", "onReceive:$p1 ")

        StopWatchFragment.inputValue= p1?.extras?.get("INPUTVALUE") as Int
        when(p1.action){
           "play" ->{

                StopWatchFragment.isCancelled=true
                 stopWatchRequest= OneTimeWorkRequestBuilder<StopWatchWorker>()
                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .setInputData(workDataOf("INPUT" to StopWatchFragment.inputValue))
                    .build()

                p0?.let {
                    WorkManager.getInstance(it)
                        .enqueueUniqueWork("FirstWork", ExistingWorkPolicy.REPLACE, stopWatchRequest)
                }
            }
            "pause"->{
                StopWatchFragment.isCancelled=false
                p0?.let { WorkManager.getInstance(it).cancelAllWork()
                }
            }
        }
    }
}