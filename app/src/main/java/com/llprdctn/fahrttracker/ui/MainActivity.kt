package com.llprdctn.fahrttracker.ui

import android.app.*
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
import com.llprdctn.fahrttracker.other.Constants.MESSAGE_EXTRA
import com.llprdctn.fahrttracker.other.Constants.NOTIFICATION_ID
import com.llprdctn.fahrttracker.other.Constants.TITLE_EXTRA
import com.llprdctn.fahrttracker.other.Notification
import com.llprdctn.fahrttracker.ui.add.AddFragment
import com.llprdctn.fahrttracker.ui.drives.DrivesFragment
import com.llprdctn.fahrttracker.ui.passengers.PassengersFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.selectedItemId = R.id.itAdd


        createNotificationChannel()
        scheduleNotification()


        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        Timber.i("$year, $month, $day, $hour, $minute")








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

    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, Notification::class.java)
        val title = "Fahrt Tracker"
        val message = "Füge deine letzte fahrt hinzu"
        intent.putExtra(TITLE_EXTRA, title)
        intent.putExtra(MESSAGE_EXTRA, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )



    }

    /*private fun showAlert(time: Long, title: String, message: String) {

        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Notification")
            .setMessage("Message")
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }*/

    private fun getTime(): Long {

        val minute = 50
        val hour = 16
        val day = 4
        val month = 11
        val year = 2021

        val calender = Calendar.getInstance()
        Timber.i(calender.timeInMillis.toString())
        calender.set(year, month, day, hour, minute)
        Timber.i(calender.timeInMillis.toString())
        return calender.timeInMillis


    }


    private fun createNotificationChannel() {
        val name = "Notifi Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }







}