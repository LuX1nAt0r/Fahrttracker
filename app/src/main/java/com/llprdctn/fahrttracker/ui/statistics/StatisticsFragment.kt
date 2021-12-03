package com.llprdctn.fahrttracker.ui.statistics

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.data.entities.Drive
import com.llprdctn.fahrttracker.other.Constants.DISTANCE
import com.llprdctn.fahrttracker.other.Constants.FUEL_PRICE
import com.llprdctn.fahrttracker.other.Constants.FUEL_USAGE_PER_KM
import com.llprdctn.fahrttracker.other.NotificationHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import timber.log.Timber
import java.sql.Timestamp
import java.util.*

@AndroidEntryPoint
class StatisticsFragment: Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()
    private var allDrives: MutableList<Drive> = mutableListOf()
    private val c = Calendar.getInstance()
    private val month = c.get(Calendar.MONTH)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()



        //Testing purpose


        button.setOnClickListener {
            val intent = Intent(requireActivity(), NotificationHandler::class.java)
            startActivity(intent)


        }


        //Testing end










    }

    private fun subscribeToObservers() {
        viewModel.allDrives.observe(viewLifecycleOwner, Observer {
            allDrives = it.toMutableList()
            tvDrivesCount.text = it.size.toString()
            tvtotalDistanceCount.text = (it.size * DISTANCE).toString() + "km"
            val price = ((it.size * DISTANCE).toFloat() * FUEL_USAGE_PER_KM * FUEL_PRICE)
            tvMoneyCount.text = "%.2f".format(price) + " €"


            val monthlyDrives = it.filter { drive ->
                val driveMonth = drive.date.dropLast(5)
                driveMonth.endsWith(month.toString(), false)
            }
            tvMonthlyDistanceCount.text = (monthlyDrives.size * DISTANCE).toString() + "km"

        })
    }
}
