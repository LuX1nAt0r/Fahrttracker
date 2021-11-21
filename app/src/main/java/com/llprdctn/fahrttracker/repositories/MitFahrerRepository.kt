package com.llprdctn.fahrttracker.repositories

import androidx.lifecycle.LiveData
import com.llprdctn.fahrttracker.data.dao.MitFahrerDao
import com.llprdctn.fahrttracker.data.entities.MitFahrer
import javax.inject.Inject

class MitFahrerRepository @Inject constructor(
    private val mitFahrerDao: MitFahrerDao
) {

    fun getAllMitFahrer(): LiveData<List<MitFahrer>> = mitFahrerDao.getAllMitFahrer()

    suspend fun insertMitFahrer(mitFahrer: MitFahrer) {
        mitFahrerDao.insertMitFahrer(mitFahrer)
    }

    suspend fun deleteMitFahrer(mitFahrer: MitFahrer) {
        mitFahrerDao.deleteMitFahrer(mitFahrer)
    }

    suspend fun getPassengerById(id: Int): MitFahrer? {
        return mitFahrerDao.getMitFahrerByID(id)
    }

    suspend fun updatePassenger(passenger: MitFahrer) {
        mitFahrerDao.updateMitFahrer(passenger)
    }


}