package com.llprdctn.fahrttracker.ui.detailsPassengers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PassengerDetailsFragment: Fragment(R.layout.fragment_passenger_details) {

    val args: PassengerDetailsFragmentArgs by navArgs()
    val viewModel: PassengerDetailsViewModel by viewModels()
    lateinit var passenger: MitFahrer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPassengerById(args.id)
        Timber.i(viewModel.currentPassenger?.name)
    }
}