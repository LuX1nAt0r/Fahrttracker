package com.llprdctn.fahrttracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.llprdctn.fahrttracker.data.dao.DriveDao
import com.llprdctn.fahrttracker.data.dao.MitFahrerDao
import com.llprdctn.fahrttracker.data.entities.Drive
import com.llprdctn.fahrttracker.data.entities.MitFahrer

@Database(
    entities = [Drive::class, MitFahrer::class],
    version = 1
)

abstract class Database: RoomDatabase() {
    abstract fun driverDao(): DriveDao
    abstract fun mitFahrerDao(): MitFahrerDao

}