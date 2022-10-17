package com.example.nickelffoxassignments_sheenu.stopwatch.data.local

import android.content.Context
import android.content.Intent
import androidx.lifecycle.*
import androidx.work.*
import com.example.nickelffoxassignments_sheenu.stopwatch.utils.TimerService

class StopWatchWorker(var context:Context, params: WorkerParameters) :CoroutineWorker(context, params) {

    companion object{
        var playButtonLiveData=MutableLiveData<Boolean>()

    }

     private var seconds=0

    override suspend fun doWork(): Result {
        try {

            seconds = inputData.getInt("INPUT", 30)

            if(!isStopped){
                val intent=Intent(context,TimerService::class.java)
                intent.putExtra("SECONDS",seconds)
                context.startService(intent)

            }
            while (!isStopped){
                playButtonLiveData.postValue(true)
            }

            return Result.success()

        }finally {
            playButtonLiveData.postValue(false)
        }


    }

}