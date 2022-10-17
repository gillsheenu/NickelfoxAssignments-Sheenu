package com.example.nickelffoxassignments_sheenu.stopwatch.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.nickelffoxassignments_sheenu.stopwatch.data.local.StopWatchRepository
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.view.StopWatchFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationIntentService  : BroadcastReceiver() {


      @Inject
      lateinit var repository:StopWatchRepository


    override fun onReceive(p0: Context?, p1: Intent) {

        when(p1.action){
           "play" -> {


               if (!StopWatchFragment.isCancelled) {

                       repository.createRequest()
                       StopWatchFragment.isCancelled=true

               }
           }

            "pause"->{

                    StopWatchFragment.isCancelled=false
                    repository.pauseRequest()
            }
            "cancel"->{

                repository.stopRequest()

            }
        }
    }
}