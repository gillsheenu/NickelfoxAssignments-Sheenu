package com.example.nickelffoxassignments_sheenu.stopwatch.data.local

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import androidx.work.*
import com.example.nickelffoxassignments_sheenu.R.*
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.view.StopWatchFragment
import com.example.nickelffoxassignments_sheenu.stopwatch.utils.NotificationIntentService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import java.util.*

class StopWatchWorker(context:Context, params: WorkerParameters) :CoroutineWorker(context, params) {

    companion object{
        var workerLiveData= MutableLiveData<Int>()
        var updateLiveData=MutableLiveData<Int>()
        var playButtonLiveData=MutableLiveData<Boolean>()


    }

     private var seconds=0
    private var hours:Int=0
    private var min:Int = 0
    private var sec:Int=0

    var time:String=String.format(Locale.getDefault(), "%d:%02d:%02d",hours,min,sec)


    private val notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override suspend fun doWork(): Result {
        try {
            seconds = inputData.getInt("INPUT", 30)

            while (!isStopped) {
                StopWatchFragment.isPlayButton=true
                hours = seconds / 3600
                min = (seconds % 3600) / 60
                sec = seconds % 60
                time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, min, sec)
                setForeground(getForegroundInfo())
                workerLiveData.postValue(seconds)
                playButtonLiveData.postValue(true)
                delay(1000)
                seconds++
            }
            return Result.success()
        }catch (e:CancellationException){
            workerLiveData.postValue(0)
            updateLiveData.postValue(seconds)
            playButtonLiveData.postValue(false)

            return Result.failure()
        }
//        }finally {
//            workerLiveData.postValue(0)
//            updateLiveData.postValue(seconds)
//        }


    }

    @SuppressLint("UnspecifiedImmutableFlag")
    override suspend fun getForegroundInfo(): ForegroundInfo {
        val intent = WorkManager.getInstance(applicationContext)
            .createCancelPendingIntent(id)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
        val pendingIntent= Intent(applicationContext, StopWatchFragment::class.java).let {
            PendingIntent.getActivity(applicationContext, 0, it,PendingIntent.FLAG_IMMUTABLE)
        }
        val playIntent=Intent(applicationContext,NotificationIntentService::class.java)
        playIntent.action = "play"
        playIntent.putExtra("INPUTVALUE",seconds)

        val playPendingIntent=PendingIntent.getBroadcast(applicationContext,0,playIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        val pauseIntent=Intent(applicationContext,NotificationIntentService::class.java)
        pauseIntent.action = "pause"
        pauseIntent.putExtra("INPUTVALUE",seconds)

        val pausePendingIntent=PendingIntent.getBroadcast(applicationContext,0,pauseIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        val notification=NotificationCompat.Builder(applicationContext,"MyStopWatch")
            .setContentTitle("StopWatch")
            .setSmallIcon(drawable.stopwatch)
            .addAction(drawable.ic_cancel,"cancel",intent)
            .setContentIntent(pendingIntent)
            .setOnlyAlertOnce(true)
            .addAction(drawable.pause,"Pause",pausePendingIntent)
            .addAction(drawable.play_button,"Play",playPendingIntent)
            //.setWhen(System.currentTimeMillis())
           // .setUsesChronometer(true)
            .setContentText(time)
            .setAutoCancel(false)
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