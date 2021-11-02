package com.llprdctn.fahrttracker.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.llprdctn.fahrttracker.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment: Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvAddDrive.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFragmentAddEditDrive()
            findNavController().navigate(action)
        }

        cvAddMitfahrer.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToFragmentAddEditMitfahrer()
            findNavController().navigate(action)
        }

    }

}