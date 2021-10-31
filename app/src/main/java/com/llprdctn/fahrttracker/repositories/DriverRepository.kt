package com.llprdctn.fahrttracker.repositories

import androidx.lifecycle.LiveData
import com.llprdctn.fahrttracker.data.dao.DriveDao
import com.llprdctn.fahrttracker.data.entities.Drive
import javax.inject.Inject

class DriverRepository @Inject constructor(
    private val driveDao: DriveDao
) {


    suspend fun insertDrive(drive: Drive) {
        driveDao.insertFahrt(drive)
    }

    suspend fun deleteDrive(drive: Drive) {
        driveDao.deleteFahrt(drive)
    }

    fun getAllDrives(): LiveData<Drive>{
        return driveDao.getAllDrives()
    }

}