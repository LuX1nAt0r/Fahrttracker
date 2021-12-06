package com.llprdctn.fahrttracker.ui.addEditDrive

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.data.entities.Drive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_edit_drive.*
import java.util.*
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import timber.log.Timber
import java.sql.Time

@AndroidEntryPoint
class AddEditDriveFragment: Fragment(R.layout.fragment_add_edit_drive) {

    //ViewModel
    private val addEditDriveViewModel: AddEditDriveViewModel by viewModels()

    //Current date
    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)
    private var allPassengers: MutableList<MitFahrer> = mutableListOf()

    //Selected date
    private var selectedMonth = month
    private var selectedDay = dayOfMonth
    private var selectedYear = year

    //Selected Chips Hin- and RueckFahrt
    private var isHinFahrtChecked = false
    private var isRueckFahrtChecked = false



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        updateDateTextView()


        //OnClick DatePickerDialog
        tvDate.setOnClickListener { showDatePickerDialog() }


        //Chip HinFahrt changeListener
        hinFahrt.setOnCheckedChangeListener { _, isChecked ->
            isHinFahrtChecked = isChecked
            updateChipGroupVisibility()
        }

        //Chip RueckFahrt changeListener
        rueckFahrt.setOnCheckedChangeListener { _, isChecked ->
            isRueckFahrtChecked = isChecked
            updateChipGroupVisibility()
        }



        //Saving behavior
        fabSaveFahrt.setOnClickListener {

            val date = "$selectedDay.$selectedMonth.$selectedYear"
            val checkedNames: List<MitFahrer> = getCheckedNamesFirstChipGroup()
            val checkedNamesWhenBothChecked: List<MitFahrer> = getCheckedNamesSecondChipGroup()
            val hinRueckFahrt = if (hinFahrt.isChecked) "HinFahrt" else "RueckFahrt"
            val checkedNamesArray: MutableList<String> = mutableListOf()
            val checkedNamesWhenBothCheckedArray: MutableList<String> = mutableListOf()

/*
            if(isHinFahrtChecked && isRueckFahrtChecked) {
                //Both chips are checked
                checkedNames = getCheckedNamesFirstChipGroup()
                checkedNamesWhenBothChecked = getCheckedNamesSecondChipGroup()
            } else {
                //Only one chip is checked
                checkedNames = getCheckedNamesFirstChipGroup()
                checkedNamesWhenBothChecked = emptyList()
            }*/


            val allPassengersToUpdate: MutableList<MitFahrer> = getCheckedNamesFirstChipGroup().toMutableList()
            //checkedNamesWhenBothChecked = getCheckedNamesSecondChipGroup()

            for (i in checkedNamesWhenBothChecked.indices) {
                val passenger = checkedNamesWhenBothChecked[i]


                val samePassenger = allPassengersToUpdate.indexOfFirst { it.id == passenger.id }
                if (samePassenger != -1) {

                    allPassengersToUpdate[samePassenger] = MitFahrer(
                        passenger.id,
                        passenger.name,
                        passenger.rides+1)
                } else {
                    allPassengersToUpdate.add(passenger)
                }
            }


            for (i in allPassengersToUpdate.indices) {
                val passenger = allPassengersToUpdate[i]
                //Update Passenger
                val mitFahrer = MitFahrer(
                    passenger.id,
                    passenger.name,
                    passenger.rides+1)
                addEditDriveViewModel.updatePassengers(mitFahrer)
            }





            //Loop through the selected Names of the first ChipGroup
            for (i in checkedNames.indices) {
                //Add checkedNames
                checkedNamesArray.add(i, checkedNames[i].name)
                //Update Passenger
                /*val mitFahrer = MitFahrer(
                    checkedNames[i].id,
                    checkedNames[i].name,
                    checkedNames[i].rides+1)
                addEditDriveViewModel.updatePassengers(mitFahrer)*/
            }

            if(isHinFahrtChecked && isRueckFahrtChecked) {
                //Both chips are checked

                //Loop through the selected Names of the second ChipGroup
                for (i in checkedNamesWhenBothChecked.indices) {
                    //Add checkedNames
                    checkedNamesWhenBothCheckedArray.add(i, checkedNamesWhenBothChecked[i].name)
                    //Update Passenger
                    /*val mitFahrer = MitFahrer(
                        checkedNamesWhenBothChecked[i].id,
                        checkedNamesWhenBothChecked[i].name,
                        checkedNamesWhenBothChecked[i].rides+1
                    )
                    addEditDriveViewModel.updatePassengers(mitFahrer)*/
                }
            }





            //Adding Driver
            val drive = Drive(hinRueckFahrt,date, checkedNamesArray,null)
            addEditDriveViewModel.addDrive(drive)

            //Adding second Drive if both are selected
            if (isHinFahrtChecked && isRueckFahrtChecked) {
                val secondDrive = Drive(
        "RueckFahrt",
                    date,
                    checkedNamesWhenBothCheckedArray,
                    null
                    )
                addEditDriveViewModel.addDrive(secondDrive)
            }



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


    private fun getCheckedNamesFirstChipGroup(): List<MitFahrer> {
        val checkedIDs = cgMitFahrer.checkedChipIds
        val mitFahrer: MutableList<MitFahrer> = mutableListOf()
        for (i in 0 until checkedIDs.size) {
            allPassengers.find { it.id == checkedIDs[i] }?.let { mitFahrer.add(it) }

        }
        return mitFahrer.toList()
    }


    private fun getCheckedNamesSecondChipGroup(): List<MitFahrer> {
        val checkedIDs = cgMitFahrerRF.checkedChipIds
        val mitFahrer: MutableList<MitFahrer> = mutableListOf()
        for (i in 0 until checkedIDs.size) {
            allPassengers.find { it.id == checkedIDs[i] }?.let { mitFahrer.add(it)  }
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
            //Create First ChipGroup
            cgMitFahrer.addView(Chip(requireContext()).apply {
                id = it.id!!
                text = it.name
                isCheckable = true
            })

            //Create second ChipGroup which shows when hin and rueckFahrt are selected
            cgMitFahrerRF.addView(Chip(requireContext()).apply {
                id = it.id!!
                text = it.name
                isCheckable = true
            })
        }

    }

    //Update the visibility of the ChipGroup if hin and rueckFahrt changes
    private fun updateChipGroupVisibility() {

        if (isHinFahrtChecked && isRueckFahrtChecked){
            tvHinfahrt.visibility = View.VISIBLE
            tvRueckFahrt.visibility = View.VISIBLE
            cgMitFahrerRF.visibility = View.VISIBLE
        } else {
            tvHinfahrt.visibility = View.INVISIBLE
            tvRueckFahrt.visibility = View.INVISIBLE
            cgMitFahrerRF.visibility = View.INVISIBLE
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
        isHinFahrtChecked = true
        val currentDate = "$dayOfMonth.${month+1}.$year"
        tvDate.text = currentDate
    }


}