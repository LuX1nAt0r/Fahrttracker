package com.llprdctn.fahrttracker.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.llprdctn.fahrttracker.repositories.DriverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
public class AddEditDriveViewModel @Inject constructor(
    private val repository: DriverRepository
): ViewModel() {



    val currentDate = MutableLiveData<String>(null)

}