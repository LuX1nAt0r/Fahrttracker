package com.llprdctn.fahrttracker.ui

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.other.Constants.CHANNEL_ID
import com.llprdctn.fahrttracker.other.Constants.MESSAGE_EXTRA
import com.llprdctn.fahrttracker.other.Constants.NOTIFICATION_ID
import com.llprdctn.fahrttracker.other.Constants.SHARED_PREF_LAST_NOTIFICATION_TIME
import com.llprdctn.fahrttracker.other.Constants.TITLE_EXTRA
import com.llprdctn.fahrttracker.other.Notification
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //TODO: Create a settingsoption to cancel notifications
    private val showNotifications = true
    private lateinit var sharedPref: SharedPreferences


    //TODO: Notifications aren't working


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = getPreferences(Context.MODE_PRIVATE)

        bottom_navigation.selectedItemId = R.id.itAdd

        val nextNotification = sharedPref.getLong(SHARED_PREF_LAST_NOTIFICATION_TIME, 0)
        val calendar = Calendar.getInstance()


        if (nextNotification < calendar.timeInMillis && showNotifications) {
            createNotificationChannel()
            scheduleNotification()
        }


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


    private fun getTime(): Long {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)

        //Get maximums of Day and Month
        val maxDay = calendar.getMaximum(Calendar.DAY_OF_MONTH)
        val maxMonth = calendar.getMaximum(Calendar.MONTH)

        //Variables for the date of the next notification
        var setDay = day
        var setMonth = month
        var setYear = year

        //Check if in range between sunday and thursday
        if (weekDay in 1..5) {
            //Check if current day isn't last day of month
            if (day != maxDay) {
                setDay = day+1
            } else {
                setDay = 1
                //Check if current month isn't last month
                if (month != maxMonth) {
                    //Isn't last month
                    setMonth = month+1
                }
                else {
                    //Is last month
                    setMonth = 0
                    setYear = year+1
                }
            }
        }
        //Else it is Friday or Saturday
        else {
            when (weekDay) {
                //Friday
                6 -> {
                    for (i in 1..3) {
                        if (setDay != maxDay){
                            setDay++
                        } else{
                            setDay=1
                            if (setMonth != maxMonth) {
                                setMonth++
                            } else {
                                setYear++
                                setMonth = 0
                            }
                        }
                    }

                }
                //Saturday
                7 -> {
                    for (i in 1..2) {
                        if (setDay != maxDay){
                            setDay++
                        } else{
                            setDay=1
                            if (setMonth != maxMonth) {
                                setMonth++
                            } else {
                                setYear++
                                setMonth = 0
                            }
                        }
                    }

                }

            }
        }
        //Set the calender variable to the next Notification time
        calendar.set(setYear, setMonth, setDay, 18, 0)

        //Save Timestamp of the next notification in sharedPref
        sharedPref.edit().putLong(
            SHARED_PREF_LAST_NOTIFICATION_TIME,
            calendar.timeInMillis
        ).apply()



        return calendar.timeInMillis
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