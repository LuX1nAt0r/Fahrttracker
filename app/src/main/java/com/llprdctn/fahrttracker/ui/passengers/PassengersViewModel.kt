package com.llprdctn.fahrttracker.ui.passengers

import androidx.lifecycle.ViewModel
import com.llprdctn.fahrttracker.repositories.MitFahrerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PassengersViewModel @Inject constructor(
    private val repository: MitFahrerRepository
): ViewModel() {

    private val _allPassengers = repository.getAllMitFahrer()

    var allPassengers = _allPassengers

}