package com.llprdctn.fahrttracker.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.llprdctn.fahrttracker.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_edit_drive.*
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class FragmentAddEditDrive: Fragment(R.layout.fragment_add_edit_drive) {

    private val viewModel: AddEditDriveViewModel by viewModels()

    private val c = Calendar.getInstance()
    private val year = c.get(Calendar.YEAR)
    private val month = c.get(Calendar.MONTH)
    private val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDate = "$dayOfMonth.$month.$year"
        tvTest.text = currentDate
        tvTest.setOnClickListener {
            showDatePickerDialog()
        }

        val names = arrayOf("Luisa", "Lukas")

        names.forEach {
            cgMitFahrer.addView(Chip(requireContext()).apply {
                id = View.generateViewId()
                text = it
                isCheckable = true

            })
        }

        cgMitFahrer.setOnCheckedChangeListener { group, checkedId ->

        }
        fabSaveFahrt.setOnClickListener {
            Timber.i(cgMitFahrer.checkedChipIds.toString())


            val checkedIDs = cgMitFahrer.checkedChipIds
            val mitFahrer: MutableList<String> = mutableListOf()

            for (i in 0 until checkedIDs.size) {
                mitFahrer.add(names[checkedIDs[i]-1])
            }
            Timber.i(mitFahrer.toString())

        }



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


}