package com.custommentoring.project.navscaffold.services

import com.custommentoring.project.navscaffold.data.MovieWrapper
import com.custommentoring.project.navscaffold.data.PlanetWrapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PlanetService {

    @GET("planets")
    suspend fun getPlanets() : PlanetWrapper

    companion object {
        var planetService :PlanetService ?= null
        fun getInstance() :PlanetService {
            if( planetService == null ) {
                planetService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PlanetService::class.java)
            }
            return planetService!!
        }
    }
}