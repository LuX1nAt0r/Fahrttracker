package com.llprdctn.fahrttracker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.llprdctn.fahrttracker.data.entities.MitFahrer

@Dao
interface MitFahrerDao {

    @Insert
    suspend fun insertMitFahrer(mitFahrer: MitFahrer)

    @Delete
    suspend fun deleteMitFahrer(mitFahrer: MitFahrer)

    @Query("SELECT * FROM mitFahrer")
    fun getAllMitFahrer(): LiveData<List<MitFahrer>>

    @Query("SELECT * FROM mitFahrer WHERE id = :id")
    suspend fun getMitFahrerByID(id: Int) : MitFahrer?
}