package com.llprdctn.fahrttracker.ui.passengers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.llprdctn.fahrttracker.R
import com.llprdctn.fahrttracker.adapters.PassengersAdapter
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragement_passengers.*

@AndroidEntryPoint
class PassengersFragment: Fragment(R.layout.fragement_passengers) {

    private val viewModel: PassengersViewModel by viewModels()
    private var allPassengers: List<MitFahrer> = emptyList()
    private lateinit var passengersAdapter: PassengersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()
        setUpRecyclerView()

    }


    private fun subscribeToObservers() {
        viewModel.allPassengers.observe(viewLifecycleOwner, Observer {
            allPassengers = it
        })
    }

    private fun setUpRecyclerView() = rvPassengers.apply {
        passengersAdapter = PassengersAdapter()
        adapter = passengersAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}