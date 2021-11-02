package com.llprdctn.fahrttracker.ui.drives

import androidx.lifecycle.ViewModel
import com.llprdctn.fahrttracker.repositories.DriverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DriveViewModel @Inject constructor(
    private val repository: DriverRepository
): ViewModel() {

    private val _allDrives = repository.getAllDrives()

    val allDrives = _allDrives

}