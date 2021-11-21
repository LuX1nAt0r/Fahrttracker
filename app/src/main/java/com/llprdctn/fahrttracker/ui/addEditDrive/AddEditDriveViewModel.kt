package com.llprdctn.fahrttracker.ui.addEditDrive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llprdctn.fahrttracker.data.entities.Drive
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import com.llprdctn.fahrttracker.repositories.DriverRepository
import com.llprdctn.fahrttracker.repositories.MitFahrerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class AddEditDriveViewModel @Inject constructor(
    private val driverRepository: DriverRepository,
    private val passengerViewModel: MitFahrerRepository
): ViewModel() {


    private val _allPassengers = passengerViewModel.getAllMitFahrer()

    val allPassengers = _allPassengers

    fun addDrive(drive: Drive) =viewModelScope.launch {
        driverRepository.insertDrive(drive)
    }

    fun updatePassengers(passenger: MitFahrer) = viewModelScope.launch {
        passengerViewModel.updatePassenger(passenger)
    }


}