package com.example.androidnotifications

import android.app.Notification.DEFAULT_LIGHTS
import android.app.NotificationChannel
import android.app.NotificationManager
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

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get the notification manager, lets try without as notificationmanager
        // nope definitely need to cast it
        val channelId = "$packageName.channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn_notification.setOnClickListener {
            intent = Intent(this, FullscreenActivity::class.java )
            intent.putExtra("nPass", "notibutton pressed")
            startActivity(intent)
        //check to make sure build version is sufficient for importance
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "Notification Channel"
                //to  the bottom of the list with imporance_min as well, neat
                val importance = NotificationManager.IMPORTANCE_MIN
                val description = "testing notifcations"

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description
                notificationManager.createNotificationChannel(channel)
                // ok all seems pretty nonneogtiable (boilerplate)
            }
            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setDefaults(DEFAULT_LIGHTS)
                .setContentTitle("A string of title stuff")
                //lets try text super long
                .setContentText(getString(R.string.toolong))
                ///.setContentText("bodystuff going here")
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                    //TODO FIND OUT RIGHT WAY TO DO THIS
                .setColor(getResources().getColor(R.color.colorMAX))
                .setSmallIcon(R.drawable.ic_stat_name)
            notificationManager.notify(NOTIFICATION_ID_INSTANT, notificationBuilder.build())

        }


    }
}
