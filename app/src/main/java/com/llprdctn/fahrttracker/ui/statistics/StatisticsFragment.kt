package com.llprdctn.fahrttracker.ui.statistics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.llprdctn.fahrttracker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment: Fragment(R.layout.fragment_statistics) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*val barChart = getView()?.findViewById<BarChart>(R.id.barChart)
        BarEntry()
        BarDataSet(List<BarEntry>)*/

    }
}