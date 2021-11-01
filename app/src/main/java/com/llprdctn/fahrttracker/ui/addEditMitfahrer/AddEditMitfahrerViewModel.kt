package com.llprdctn.fahrttracker.ui.addEditMitfahrer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import com.llprdctn.fahrttracker.repositories.MitFahrerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditMitfahrerViewModel @Inject constructor(
    val repository: MitFahrerRepository
): ViewModel() {

    fun addMitFahrer(mitFahrer: MitFahrer) = viewModelScope.launch {
        repository.insertMitFahrer(mitFahrer)
    }

}