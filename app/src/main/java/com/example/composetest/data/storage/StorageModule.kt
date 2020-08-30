package com.example.composetest.data.storage

import android.content.Context
import androidx.room.Room
import com.example.composetest.data.storage.country.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()
    }

    @Provides
    fun provideCountryDao(db: AppDatabase): CountryDao {
        return db.countryDao()
    }
}