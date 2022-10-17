package com.example.nickelffoxassignments_sheenu.stopwatch.utils

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.content.ContextCompat
import java.util.*

class TimerService: Service() {
    private var timer=Timer()

    private var hours:Int=0
    private var min:Int = 0
    private var sec:Int=0

    var time:String=String.format(Locale.getDefault(), "%d:%02d:%02d",hours,min,sec)


    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time=intent.getIntExtra("SECONDS",0)
        timer.scheduleAtFixedRate(TimeTask(time),0,1000)

        return START_NOT_STICKY

    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()

    }

    private inner class TimeTask(private var time:Int): TimerTask() {
        override fun run() {
            val intent=Intent("timerUpdated")
            time++
            createNotification(time)
            intent.putExtra("TIME_EXTRA",time)
            sendBroadcast(intent)
        }
    }

    private fun createNotification(seconds:Int){
        val intent=Intent(applicationContext,StopWatchService::class.java)
        hours = seconds / 3600
        min = (seconds % 3600) / 60
        sec = seconds % 60
        time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, min, sec)
        intent.putExtra("UPDATED_TIME",time)
        ContextCompat.startForegroundService(applicationContext,intent)
    }
}