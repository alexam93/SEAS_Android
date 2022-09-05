package com.example.cinesaragon.data.remote

import com.example.cinesaragon.application.AppConstans
import com.example.cinesaragon.data.model.MovieList
import com.example.cinesaragon.repository.WebService

class MovieDataSource(private val webService: WebService) {

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstans.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstans.API_KEY)

    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstans.API_KEY)
}