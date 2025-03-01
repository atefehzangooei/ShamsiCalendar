package com.appcoding.movie.domain

import com.appcoding.movie.models.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // https://moviesapi.ir/api/v1/movies?/page=1

    @GET("movies?")
    suspend fun getMovies(
        @Query("page")page: Int
    ):Response<MoviesList>
}