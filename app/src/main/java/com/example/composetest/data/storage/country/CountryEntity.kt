package com.example.composetest.data.storage.country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class CountryEntity(
    @PrimaryKey
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "population") val population: Long,
    @ColumnInfo(name = "flag_url") val flagUrl: String,
    @ColumnInfo(name = "borders") val borders: String,
)
