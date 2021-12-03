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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.selectedItemId = R.id.itAdd










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








}