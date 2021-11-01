package com.llprdctn.fahrttracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mitFahrer")
data class MitFahrer(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val rides: Int
)
