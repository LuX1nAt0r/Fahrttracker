package com.llprdctn.fahrttracker.repositories

import androidx.lifecycle.LiveData
import com.llprdctn.fahrttracker.data.dao.MitFahrerDao
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import javax.inject.Inject

class MitFahrerRepository @Inject constructor(
    private val mitFahrerDao: MitFahrerDao
) {

    fun getAllMitFahrer(): LiveData<MitFahrer> = mitFahrerDao.getAllMitFahrer()

    suspend fun insertMitFahrer(mitFahrer: MitFahrer) {
        mitFahrerDao.insertMitFahrer(mitFahrer)
    }

    suspend fun deleteMitFahrer(mitFahrer: MitFahrer) {
        mitFahrerDao.deleteMitFahrer(mitFahrer)
    }


}