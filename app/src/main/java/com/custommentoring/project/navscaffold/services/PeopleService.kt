package com.custommentoring.project.navscaffold.services

import com.custommentoring.project.navscaffold.data.PeopleWrapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://swapi.dev/api/"

interface PeopleService {

    @GET("people")
    suspend fun getPeople() : PeopleWrapper

    companion object {
        var peopleService :PeopleService ?= null
        fun getInstance() :PeopleService {
            if( peopleService == null ) {
                peopleService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(PeopleService::class.java)
            }
            return peopleService!!
        }
    }
}