package com.example.movieworld.network

import com.example.movieworld.model.MovieResponse
import retrofit2.http.GET

interface TmdbService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieResponse //metodun com.example.movieworldapplication.model.MovieResponse tipinde değer verilmesini istiyoruz

}