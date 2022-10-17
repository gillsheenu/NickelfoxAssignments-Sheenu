package com.example.nickelffoxassignments_sheenu.stopwatch.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.nickelffoxassignments_sheenu.R
import com.example.nickelffoxassignments_sheenu.stopwatch.ui.view.StopWatchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StopWatchService: Service() {

      private lateinit var notificationManager:NotificationManager
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        createNotification()

          notificationManager=applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }

        val time=intent.getStringExtra("UPDATED_TIME")

        val moveIntent= Intent(applicationContext, StopWatchFragment::class.java)

           val movePendingIntent= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getActivity(applicationContext, 0,moveIntent ,PendingIntent.FLAG_MUTABLE)
            } else {
               PendingIntent.getActivity(applicationContext, 0, moveIntent,PendingIntent.FLAG_UPDATE_CURRENT)
            }



        val cancelIntent=Intent(applicationContext,NotificationIntentService::class.java)
        cancelIntent.action="cancel"

        val cancelPendingIntent= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(applicationContext,0,cancelIntent,PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getBroadcast(applicationContext,0,cancelIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val playIntent=Intent(applicationContext,NotificationIntentService::class.java)
        playIntent.action = "play"
        val playPendingIntent= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(applicationContext,0,playIntent,PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getBroadcast(applicationContext,0,playIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val pauseIntent=Intent(applicationContext,NotificationIntentService::class.java)
        pauseIntent.action = "pause"
        val pausePendingIntent= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(applicationContext,0,pauseIntent,PendingIntent.FLAG_MUTABLE)
        } else {
            PendingIntent.getBroadcast(applicationContext,0,pauseIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification=NotificationCompat.Builder(applicationContext,"MyStopWatch")
            .setContentTitle("StopWatch")
            .setSmallIcon(R.drawable.stopwatch)
            .setContentIntent(movePendingIntent)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.ic_cancel,"Cancel",cancelPendingIntent)
            .addAction(R.drawable.pause,"Pause",pausePendingIntent)
            .addAction(R.drawable.play_button,"Play",playPendingIntent)
            //.setWhen(System.currentTimeMillis())
            // .setUsesChronometer(true)
            .setContentText(time)
            .setOngoing(true)
            .build()
//        notificationManager.notify(10001,notification)

        startForeground(10001,notification)

        return START_STICKY


    }

    private fun createNotification() {

    }

    override fun onDestroy() {
        super.onDestroy()
        stopForeground(true)
        stopSelf()
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