package com.llprdctn.fahrttracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.llprdctn.fahrttracker.data.entities.MitFahrer

@Dao
interface MitFahrerDao {

    @Insert
    fun insertMitFahrer(mitFahrer: MitFahrer)

    @Delete
    fun deleteMitFahrer(mitFahrer: MitFahrer)

    @Query("SELECT * FROM mitFahrer")
    fun getAllMitFahrer()

    @Query("SELECT * FROM mitFahrer WHERE id = :id")
    fun getMitFahrerByID(id: Int)
}