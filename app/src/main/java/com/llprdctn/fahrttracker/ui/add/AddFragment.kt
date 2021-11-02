package com.llprdctn.fahrttracker.ui.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.llprdctn.fahrttracker.R
import kotlinx.android.synthetic.main.fragment_home.*


class AddFragment: Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvAddDrive.setOnClickListener {
            val action = AddFragmentDirections.actionHomeFragmentToFragmentAddEditDrive()
            findNavController().navigate(action)

        }

        cvAddMitfahrer.setOnClickListener {
            val action = AddFragmentDirections.actionHomeFragmentToFragmentAddEditMitfahrer()
            findNavController().navigate(action)
        }

    }

}