package com.llprdctn.fahrttracker.ui.drives

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.adapters.DriversAdapter
import com.llprdctn.fahrttracker.data.entities.Drive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_drives.*
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class DrivesFragment: Fragment(R.layout.fragment_drives) {

    private val driveViewModel: DriveViewModel by viewModels()
    private var allDrives: List<Drive> = emptyList()
    private lateinit var driveAdapter: DriversAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        subscribeToObservers()
        setupRecyclerView()


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val date = "$day.$month.$year"
        updateRecyclerView(date)


        //ToDO: Create dots in calendarView when there is a drive

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth.$month.$year"
            updateRecyclerView(selectedDate)
            }




    }

    private fun updateRecyclerView(date: String) {
        if(allDrives.isNotEmpty()) {
                val filteredList = allDrives.filter { it.date == date }
            driveAdapter.drives = filteredList
        }
    }



    private fun subscribeToObservers(){
        driveViewModel.allDrives.observe(viewLifecycleOwner, Observer {
            allDrives = it
        })
    }

    private fun setupRecyclerView() = rvDrives.apply {
        driveAdapter = DriversAdapter()
        adapter = driveAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}