package com.llprdctn.fahrttracker.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.llprdctn.fahrttracker.ui.AddEditDriveViewModel
import kotlinx.android.synthetic.main.fragment_add_edit_drive.*
import java.util.*

class DateDialog(): DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val viewModel: AddEditDriveViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireContext(), this, year, month, dayOfMonth)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //tilDate.setText("$dayOfMonth.$month.$year")
        val date = "$dayOfMonth.$month.$year"

        viewModel.currentDate.postValue(date)
    }
}
