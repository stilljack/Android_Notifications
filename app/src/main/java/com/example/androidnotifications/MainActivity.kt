package com.example.androidnotifications

import android.app.Notification.DEFAULT_LIGHTS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val NOTIFICATION_ID_INSTANT = 1
        const val NOTIFICATION_ID_PENDING =2

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///moved notification set up out of first button on click to save some repeat code
        val channelId = "$packageName.channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        setContentView(R.layout.activity_main)
        //get the notification manager, lets try without as notificationmanager
        // nope definitely need to cast it
        //check to make sure build version is sufficient for importance
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Channel"
            //to  the bottom of the list with imporance_min as well, neat
            val importance = NotificationManager.IMPORTANCE_MIN
            val description = "testing notifications"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            notificationManager.createNotificationChannel(channel)
            // ok all seems pretty nonneogtiable (boilerplate)
        }
        //save some more repeat code keeping this up here
        val contentIntent = Intent(this, FullscreenActivity::class.java )



        btn_notification.setOnClickListener {
            //this is specific to first on notification
            contentIntent.putExtra("nPass", "first notification button pressed")
            val pendingContentIntent = PendingIntent.getActivity(this, 0, contentIntent,PendingIntent.FLAG_ONE_SHOT)


            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setDefaults(DEFAULT_LIGHTS)
                .setContentTitle("A string of title stuff")
                //lets try text super long
                .setContentText(getString(R.string.toolong))
                ///.setContentText("bodystuff going here")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                    //TODO FIND OUT RIGHT WAY TO DO THIS
                .setColor(getResources().getColor(R.color.colorMAX))
                .setSmallIcon(R.drawable.ic_stat_name)
                .setAutoCancel(true)
                .setContentIntent(pendingContentIntent)
            notificationManager.notify(NOTIFICATION_ID_INSTANT, notificationBuilder.build())

        }

        btn_notification2.setOnClickListener{
            contentIntent.putExtra("nPass", "second notification button pressed")
            val pendingContentIntent = PendingIntent.getActivity(this, 0, contentIntent,PendingIntent.FLAG_ONE_SHOT)


            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setDefaults(DEFAULT_LIGHTS)
                .setContentTitle("BUTTON 2 PRESSED")
                //lets try text super long
                .setContentText("NOT NEARLY AS LONG")
                ///.setContentText("bodystuff going here")
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                //TODO FIND OUT RIGHT WAY TO DO THIS
                .setColor(getResources().getColor(R.color.colorMIN))
                .setSmallIcon(R.drawable.ic_local_hotel_black_24dp)
                .setAutoCancel(true)
                .setContentIntent(pendingContentIntent)
            notificationManager.notify(NOTIFICATION_ID_INSTANT, notificationBuilder.build())

        }


    }
}
