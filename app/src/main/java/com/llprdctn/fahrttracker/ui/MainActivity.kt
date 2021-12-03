package com.llprdctn.fahrttracker.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.other.Constants.CHANNEL_ID
import com.llprdctn.fahrttracker.ui.add.AddFragment
import com.llprdctn.fahrttracker.ui.drives.DrivesFragment
import com.llprdctn.fahrttracker.ui.passengers.PassengersFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.selectedItemId = R.id.itAdd
        createNotificationChannel()









        bottom_navigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.itStatistics -> {
                    NavHostFragment.findNavController(navHostFragment).navigate(
                        R.id.action_global_statisticsFragment
                    )
                }
                R.id.itAdd -> {
                    NavHostFragment.findNavController(navHostFragment).navigate(
                        R.id.action_global_homeFragment
                    )
                }
                R.id.itDriveRV -> {
                    NavHostFragment.findNavController(navHostFragment).navigate(
                        R.id.action_global_drivesFragment
                    )
                }
                R.id.itPassengerRV -> {
                    NavHostFragment.findNavController(navHostFragment).navigate(
                        R.id.action_global_passengersFragment
                    )
                }
                R.id.itSettings -> {
                    NavHostFragment.findNavController(navHostFragment).navigate(
                        R.id.action_global_settingsFragment
                    )
                }

            }
            return@setOnItemSelectedListener true
        }


        //Notification Intent, On Notification click
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
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




    }




    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionText = "DescriptionText"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



}