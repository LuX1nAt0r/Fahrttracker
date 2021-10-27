package com.llprdctn.fahrttracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.llprdctn.fahrttracker.data.entities.Drive

@Dao
interface DriveDao {

    @Query("SELECT * FROM drive")
    fun getAllDrives()

    @Insert
    fun insertFahrt(fahrt: Drive)

    @Delete
    fun deleteFahrt(fahrt: Drive)

}