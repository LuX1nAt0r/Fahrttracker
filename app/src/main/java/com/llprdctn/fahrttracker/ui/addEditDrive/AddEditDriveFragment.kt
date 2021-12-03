package com.llprdctn.fahrttracker.ui.addEditDrive

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.data.entities.Drive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_edit_drive.*
import java.util.*
import com.llprdctn.fahrttracker.data.entities.MitFahrer

@AndroidEntryPoint
class AddEditDriveFragment: Fragment(R.layout.fragment_add_edit_drive) {

    private val addEditDriveViewModel: AddEditDriveViewModel by viewModels()

    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)
    private var allPassengers: MutableList<MitFahrer> = mutableListOf()

    private var selectedMonth = month
    private var selectedDay = dayOfMonth
    private var selectedYear = year



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        updateDateTextView()


        //OnClick DatePickerDialog
        tvDate.setOnClickListener {
            showDatePickerDialog()
        }








        fabSaveFahrt.setOnClickListener {

            val date = "$selectedDay.$selectedMonth.$selectedYear"
            val checkedNames = getCheckedNames()
            val hinRueckFahrt = if (hinFahrt.isChecked) "HinFahrt" else "RueckFahrt"
            val checkedNamesArray: MutableList<String> = mutableListOf()

            for (i in checkedNames.indices) {
                //Add checkedNames
                checkedNamesArray.add(i, checkedNames[i].name)
                //Update Passenger
                val mitFahrer = MitFahrer(
                    checkedNames[i].id,
                    checkedNames[i].name,
                    checkedNames[i].rides+1)
                addEditDriveViewModel.updatePassengers(mitFahrer)
            }

            //Adding Driver
            val drive = Drive(hinRueckFahrt,date, checkedNamesArray,null)

            addEditDriveViewModel.addDrive(drive)
            Snackbar.make(
                requireView(),
                "Successfully saved Drive!",
                Snackbar.LENGTH_SHORT
            ).show()

            //Navigate to home
            findNavController().popBackStack()

        }



    }

    private fun subscribeToObservers() {
        //Get All Passengers and Update the Chips
        addEditDriveViewModel.allPassengers.observe(viewLifecycleOwner, {
            allPassengers = it.toMutableList()

            //Create ChipMenu
            if (allPassengers.isNotEmpty()) {
                createChips(allPassengers)
            }
        })
    }


    private fun getCheckedNames(): List<MitFahrer> {
        val checkedIDs = cgMitFahrer.checkedChipIds
        val mitFahrer: MutableList<MitFahrer> = mutableListOf()

        for (i in 0 until checkedIDs.size) {
            allPassengers.find { it.id == checkedIDs[i] }?.let { mitFahrer.add(it) }

        }
        return mitFahrer.toList()
    }



    //Generate DatePicker
    private fun showDatePickerDialog() {
        DatePickerDialog(requireContext(),
            { _, year, month, dayOfMonth ->
                //OnDateSetListener
                //val selectedDate = "$dayOfMonth.$month.$year"
                selectedDay = dayOfMonth
                selectedMonth = month
                selectedYear = year
                updateDateTextView()
                //tvTest.text = selectedDate
            }, year, month, dayOfMonth
        ).show()
    }

    //Generate Chips for the passengers
    private fun createChips(passengers: List<MitFahrer>){
        passengers.forEach {
            cgMitFahrer.addView(Chip(requireContext()).apply {
                id = it.id!!
                text = it.name
                isCheckable = true
            })
        }
    }


    private fun updateDateTextView() {
        val date = "$selectedDay.${selectedMonth+1}.$selectedYear"
        tvDate.text = date
    }

    override fun onResume() {
        super.onResume()
        //Initialize layout
        hinFahrt.isChecked = true
        val currentDate = "$dayOfMonth.${month+1}.$year"
        tvDate.text = currentDate
    }


}