package com.llprdctn.fahrttracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drive")
data class Drive(
    val date: String,
    val mitDriver: List<String>,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)
