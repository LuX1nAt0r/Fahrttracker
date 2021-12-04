package com.llprdctn.fahrttracker.other

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.other.Constants.CHANNEL_ID
import com.llprdctn.fahrttracker.other.Constants.MESSAGE_EXTRA
import com.llprdctn.fahrttracker.other.Constants.NOTIFICATION_ID
import com.llprdctn.fahrttracker.other.Constants.TITLE_EXTRA
import com.llprdctn.fahrttracker.ui.MainActivity


class Notification: BroadcastReceiver() {




    override fun onReceive(context: Context, intent: Intent) {

        //Notification Intent, On Notification click
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntentNotification: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(intent.getStringExtra(TITLE_EXTRA))
            .setContentText(intent.getStringExtra(MESSAGE_EXTRA))
            .setContentIntent(pendingIntentNotification)

            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(NOTIFICATION_ID, notification)



    }
}