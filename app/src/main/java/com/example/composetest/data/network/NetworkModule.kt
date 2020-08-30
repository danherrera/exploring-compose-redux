package com.example.composetest.data.network

import com.example.composetest.data.network.restcountries.RestCountriesApi
import com.example.composetest.data.network.retrofit.adapter.either.EitherCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @RestCountries
    fun provideRestCountriesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(EitherCallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRestCountriesApi(@RestCountries retrofit: Retrofit): RestCountriesApi {
        return retrofit.create(RestCountriesApi::class.java)
    }
}