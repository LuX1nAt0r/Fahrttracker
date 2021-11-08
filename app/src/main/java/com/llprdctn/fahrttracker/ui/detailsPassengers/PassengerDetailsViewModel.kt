package com.llprdctn.fahrttracker.ui.detailsPassengers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import com.llprdctn.fahrttracker.repositories.MitFahrerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PassengerDetailsViewModel @Inject constructor(
    val repository: MitFahrerRepository
): ViewModel() {

    var currentPassenger :MitFahrer? = null

    fun getPassengerById(id: Int) = viewModelScope.launch {
        currentPassenger = repository.getPassengerById(id)
    }

}