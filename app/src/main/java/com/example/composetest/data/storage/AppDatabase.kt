package com.example.composetest.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composetest.data.storage.country.CountryDao
import com.example.composetest.data.storage.country.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}