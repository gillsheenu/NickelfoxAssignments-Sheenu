package com.example.nickelffoxassignments_sheenu.stopwatch.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.example.nickelffoxassignments_sheenu.R.*
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.StopWatchFragment
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.delay

class StopWatchWorker(context:Context, params: WorkerParameters) :CoroutineWorker(context, params) {

    companion object{
        var workerLiveData= MutableLiveData<Int>()
    }

    var seconds=0

    private val notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override suspend fun doWork(): Result {

        return try{
            while(!isStopped){
                workerLiveData.postValue(seconds)
                delay(1000)
                seconds++
            }
            return Result.success()

        }finally {
            workerLiveData.postValue(0)
        }

    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        val intent = WorkManager.getInstance(applicationContext)
            .createCancelPendingIntent(id)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
        val pendingIntent= Intent(applicationContext,StopWatchFragment::class.java).let {
            PendingIntent.getActivity(applicationContext, 0, it,0)
        }

        val notification=NotificationCompat.Builder(applicationContext,"MyStopWatch")
            .setContentTitle("StopWatch")
            .setSmallIcon(drawable.stopwatch)
            .addAction(drawable.ic_cancel,"cancel",intent)
            .setContentIntent(pendingIntent)
            .setContentText("this is a stop watch")
            .setOngoing(true)
            .build()

        return ForegroundInfo(10001,notification)
    }


    private fun createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel("MyStopWatch", "MyStopWatch", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "This is stopwatch Channel"
            }
            notificationManager.createNotificationChannel(channel)
        }

    }


}