package com.llprdctn.fahrttracker.ui.addEditDrive

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llprdctn.fahrttracker.data.entities.Drive
import com.llprdctn.fahrttracker.repositories.DriverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class AddEditDriveViewModel @Inject constructor(
    private val repository: DriverRepository
): ViewModel() {


    fun addDrive(drive: Drive) =viewModelScope.launch {
        repository.insertDrive(drive)
    }

}