package com.appcoding.movie.models

data class Data(
    val country : String,
    val genres : List<String>,
    val id : Int,
    val image : List<String>,
    val imdb_rating : String,
    val poster : String,
    val title : String,
    val year : String
)
