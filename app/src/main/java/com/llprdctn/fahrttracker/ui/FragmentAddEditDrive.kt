package com.llprdctn.fahrttracker.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.llprdctn.fahrttracker.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_edit_drive.*
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

        tvTest.text = "$dayOfMonth.$month.$year"
        tvTest.setOnClickListener {
            showDatePickerDialog(view)
        }




    }

    private fun showDatePickerDialog(v:View) {


        DatePickerDialog(requireContext(),
            { _, year, month, dayOfMonth ->
                tvTest.text = "$dayOfMonth.$month.$year"
            },
            year,
            month,
            dayOfMonth
        ).show()
    }


}