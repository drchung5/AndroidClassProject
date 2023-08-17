package com.custommentoring.project.navscaffold.data

data class MovieWrapper (
    val count :Int,
    val next :String?,
    val previous :String?,
    val results :List<Movie>?
)

data class Movie (
    val title :String,
    val episode_id :Int,
    val opening_crawl :String,
    val director :String,
    val producer :String,
    val release_date :String,
    val characters :List<String>?,
    val planets :List<String>?,
    val starships :List<String>?,
    val vehicles :List<String>?,
    val species :List<String>?,
    val created :String,
    val edited :String,
    val url :String
)