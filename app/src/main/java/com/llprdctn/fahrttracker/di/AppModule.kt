package com.llprdctn.fahrttracker.di

import android.content.Context
import androidx.room.Room
import com.llprdctn.fahrttracker.data.Database
import com.llprdctn.fahrttracker.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDriveDao(db: Database) = db.driverDao()

    @Singleton
    @Provides
    fun provideMitFahrerDao(db: Database) = db.mitFahrerDao()
}