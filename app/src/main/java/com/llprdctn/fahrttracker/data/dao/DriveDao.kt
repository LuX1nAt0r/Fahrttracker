package com.llprdctn.fahrttracker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.llprdctn.fahrttracker.data.entities.Drive

@Dao
interface DriveDao {

    @Query("SELECT * FROM drive")
    fun getAllDrives(): LiveData<List<Drive>>

    //@Query("SELECT * FROM DRIVE WHERE id = :id")
    //suspend fun getDriveById(id: Int): Drive

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFahrt(fahrt: Drive)

    @Delete
    suspend fun deleteFahrt(fahrt: Drive)

}