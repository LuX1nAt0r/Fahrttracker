package com.llprdctn.fahrttracker.ui

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.ui.dialogs.DateDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_edit_drive.*
import timber.log.Timber

@AndroidEntryPoint
class FragmentAddEditDrive: Fragment(R.layout.fragment_add_edit_drive) {

    private val viewModel: AddEditDriveViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()


        tilDate.setOnFocusChangeListener { v, hasFocus ->
            tilDate.inputType = InputType.TYPE_NULL
            if (hasFocus) {
                showDatePickerDialog(view)
            }
        }



    }

    fun showDatePickerDialog(v:View) {
        DateDialog().show(activity?.supportFragmentManager!!, "datePicker")
    }

    fun subscribeToObservers() {
        viewModel.currentDate.observe(viewLifecycleOwner, Observer {
            tilDate.setText(it)
        })
    }
}