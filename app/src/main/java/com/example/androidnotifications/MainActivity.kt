package com.example.androidnotifications

import android.app.Notification.DEFAULT_LIGHTS
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val NOTIFICATION_ID_INSTANT = 1

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get the notification manager, lets try without as notificationmanager
        // nope definitely need to cast it
        val channelId = "$packageName.channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn_notification.setOnClickListener {

        //check to make sure build version is sufficient for importance
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Notification Channel"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val description = "testing notifcations"

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description
                notificationManager.createNotificationChannel(channel)
                // ok all seems pretty nonneogtiable (boilerplate)
            }
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setDefaults(DEFAULT_LIGHTS)
                .setContentTitle("A string of title stuff")
                .setContentText("bodystuff going here")
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                    //TODO FIND OUT RIGHT WAY TO DO THIS
                .setColor(getResources().getColor(R.color.colorMAX))
                .setSmallIcon(R.drawable.ic_local_hotel_black_24dp)
            notificationManager.notify(NOTIFICATION_ID_INSTANT, notificationBuilder.build())


        }

    }
}
