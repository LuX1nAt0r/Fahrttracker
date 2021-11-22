package com.llprdctn.fahrttracker.ui.statistics

import androidx.lifecycle.ViewModel
import com.llprdctn.fahrttracker.repositories.DriverRepository
import com.llprdctn.fahrttracker.repositories.MitFahrerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    driveRepository: DriverRepository,
    mitFahrerRepository: MitFahrerRepository
): ViewModel() {

    private val _allDrives = driveRepository.getAllDrives()

    val allDrives = _allDrives

    private val _allPassengers = mitFahrerRepository.getAllMitFahrer()
    val allPassengers = _allPassengers



}