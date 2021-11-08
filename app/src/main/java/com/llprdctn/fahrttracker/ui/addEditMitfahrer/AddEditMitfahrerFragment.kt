package com.llprdctn.fahrttracker.ui.addEditMitfahrer

import android.os.Bundle
import android.view.View
import androidx.core.view.isNotEmpty
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_edit_mitfahrer.*

@AndroidEntryPoint
class AddEditMitfahrerFragment: Fragment(R.layout.fragment_add_edit_mitfahrer) {
    private val viewModel: AddEditMitfahrerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tilMitfahrerName.editText?.doOnTextChanged { text, start, before, count ->
            if (text?.length!! >= 1) {
                tilMitfahrerName.error = null
            }
        }



        fabSaveMitfahrer.setOnClickListener {
            if (tilMitfahrerName.editText?.text?.toString()?.isNotEmpty() == true) {
                val name = tilMitfahrerName.editText!!.text.toString()
                val mitfahrer = MitFahrer(null, name, 0)
                viewModel.addMitFahrer(mitfahrer)
                Snackbar.make(
                    requireView(),
                    "Successfully saved Passenger!",
                    Snackbar.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            } else {
                tilMitfahrerName.error = "Please add a Name"
            }
        }

    }
}