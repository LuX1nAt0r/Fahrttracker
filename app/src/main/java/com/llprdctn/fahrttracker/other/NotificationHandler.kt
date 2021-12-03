package com.llprdctn.fahrttracker.other

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.other.Constants.NOTIFICATION_ID
import com.llprdctn.fahrttracker.ui.MainActivity

class NotificationHandler: AppCompatActivity(){

    private var notificationId = 0

    override fun onStart() {
        super.onStart()

        createNotificationChannel()



        //Notification Intent, On Notification click
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)



        // Notification Builder
        val builder = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Fahrt Tracker")
            .setContentText("Füge fahrt hinzu")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
            notificationId++
        }

       navigateBackToMainActivity()

    }
    private fun createNotificationChannel() {
            val name = Constants.CHANNEL_ID
            val descriptionText = "DescriptionText"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Constants.CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
    }

    private fun navigateBackToMainActivity() {
        val intentMainActivity = Intent(this, MainActivity::class.java)
        startActivity(intentMainActivity)
    }
}