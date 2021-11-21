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
import androidx.lifecycle.Observer
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import timber.log.Timber

@AndroidEntryPoint
class AddEditDriveFragment: Fragment(R.layout.fragment_add_edit_drive) {

    private val addEditDriveViewModel: AddEditDriveViewModel by viewModels()


    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

    private var allPassengers: MutableList<MitFahrer> = mutableListOf()

    //Just for testing
    //private val names = arrayOf("Luisa", "Lukas")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()

        hinFahrt.isChecked = true
        val currentDate = "$dayOfMonth.$month.$year"
        tvTest.text = currentDate

        //OnClick DatePickerDialog
        tvTest.setOnClickListener {
            showDatePickerDialog()
        }








        fabSaveFahrt.setOnClickListener {

            val date = tvTest.text.toString()
            val checkedNames = getCheckedNames()
            val hinRueckFahrt = if (hinFahrt.isChecked) "HinFahrt" else "RueckFahrt"
            val checkedNamesArray: MutableList<String> = mutableListOf()

            for (i in checkedNames.indices) {
                checkedNamesArray.add(i, checkedNames[i].name)
                val mitFahrer = MitFahrer(
                    checkedNames[i].id,
                    checkedNames[i].name,
                    checkedNames[i].rides+1)
                addEditDriveViewModel.updatePassengers(mitFahrer)
            }


            val drive = Drive(hinRueckFahrt,date, checkedNamesArray,null)


            addEditDriveViewModel.addDrive(drive)
            Snackbar.make(
                requireView(),
                "Successfully saved Drive!",
                Snackbar.LENGTH_SHORT
            ).show()




            findNavController().popBackStack()

        }



    }

    private fun subscribeToObservers() {
        //Get All Passengers and Update the Chips
        addEditDriveViewModel.allPassengers.observe(viewLifecycleOwner, {
            allPassengers = it.toMutableList()
            val namesArray: MutableList<String> = mutableListOf()
            for (i in 0 until it.size) {
                namesArray.add(i, it[i].name)
            }
            createChips(namesArray.toTypedArray())
            Timber.i(allPassengers.toString())
        })
    }

    private fun getCheckedNames(): List<MitFahrer> {
        val checkedIDs = cgMitFahrer.checkedChipIds
        val mitFahrer: MutableList<MitFahrer> = mutableListOf()

        for (i in 0 until checkedIDs.size) {
            mitFahrer.add(allPassengers[checkedIDs[i]-1])

        }
        Timber.i(mitFahrer.toString())
        return mitFahrer.toList()
    }

    private fun showDatePickerDialog() {
        DatePickerDialog(requireContext(),
            { _, year, month, dayOfMonth ->
                //OnDateSetListener
                val selectedDate = "$dayOfMonth.$month.$year"
                tvTest.text = selectedDate
            },
            year,
            month,
            dayOfMonth
        ).show()
    }

    private fun createChips(names: Array<String>){
        names.forEach {
            cgMitFahrer.addView(Chip(requireContext()).apply {
                id = View.generateViewId()
                text = it
                isCheckable = true

            })
        }
    }


}