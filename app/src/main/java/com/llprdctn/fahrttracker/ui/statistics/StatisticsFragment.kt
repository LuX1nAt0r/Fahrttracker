package com.llprdctn.fahrttracker.ui.statistics

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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_statistics.*
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class StatisticsFragment: Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()
    private var allDrives: MutableList<Drive> = mutableListOf()
    private val c = Calendar.getInstance()
    private val month = c.get(Calendar.MONTH)+1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()

    }

    private fun subscribeToObservers() {
        viewModel.allDrives.observe(viewLifecycleOwner, Observer {
            allDrives = it.toMutableList()
            tvDrivesCount.text = it.size.toString()
            tvtotalDistanceCount.text = (it.size * DISTANCE).toString() + "km"
            val price = ((it.size * DISTANCE).toFloat() * FUEL_USAGE_PER_KM * FUEL_PRICE)

            tvMoneyCount.text = "%.2f".format(price)+ " €"
            val string = "21.9.2190"
            val test = string.find { it == "."[0] }
            Timber.i(test.toString())


            //val mothlyDrives = it.filter {  }

            //tvMonthlyDistanceCount.text = it.filter { it.date. }
        })
    }
}