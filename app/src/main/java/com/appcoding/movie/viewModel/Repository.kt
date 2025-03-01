package com.appcoding.movie.viewModel

import com.appcoding.movie.models.MoviesList
import com.appcoding.movie.utils.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getMovieList(page:Int):Response<MoviesList>{
        return RetrofitInstance.api.getMovies(page)
    }
}