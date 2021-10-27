package com.llprdctn.fahrttracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drive")
data class Drive(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Long,
    val mitFahrer: List<Int>
)
