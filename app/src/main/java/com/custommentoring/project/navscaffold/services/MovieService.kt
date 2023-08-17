package com.custommentoring.project.navscaffold.services

import com.custommentoring.project.navscaffold.data.MovieWrapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface  MovieService {

    @GET("films")
    suspend fun getMovies() : MovieWrapper

    companion object {
        var movieService :MovieService ?= null
        fun getInstance() :MovieService {
            if( movieService == null ) {
                movieService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieService::class.java)
            }
            return movieService!!
        }
    }
}