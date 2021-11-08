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

@AndroidEntryPoint
class AddEditDriveFragment: Fragment(R.layout.fragment_add_edit_drive) {

    private val viewModel: AddEditDriveViewModel by viewModels()

    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

    //Just for testing
    private val names = arrayOf("Luisa", "Lukas")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hinFahrt.isChecked = true
        val currentDate = "$dayOfMonth.$month.$year"
        tvTest.text = currentDate
        tvTest.setOnClickListener {
            showDatePickerDialog()
        }

        createChips()






        fabSaveFahrt.setOnClickListener {

            val date = tvTest.text.toString()
            val checkedNames = getCheckedNames()
            val hinRueckFahrt = if (hinFahrt.isChecked) "HinFahrt" else "RueckFahrt"


            val drive = Drive(hinRueckFahrt,date, checkedNames,null)


            viewModel.addDrive(drive)
            Snackbar.make(
                requireView(),
                "Successfully saved Drive!",
                Snackbar.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()

        }



    }

    private fun getCheckedNames(): List<String> {
        val checkedIDs = cgMitFahrer.checkedChipIds
        val mitFahrer: MutableList<String> = mutableListOf()

        for (i in 0 until checkedIDs.size) {
            mitFahrer.add(names[checkedIDs[i]-1])
        }
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

    private fun createChips(){
        names.forEach {
            cgMitFahrer.addView(Chip(requireContext()).apply {
                id = View.generateViewId()
                text = it
                isCheckable = true

            })
        }
    }


}