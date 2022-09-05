package com.example.cinesaragon.repository

import com.example.cinesaragon.data.model.MovieList
import com.example.cinesaragon.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList = dataSource.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSource.getPopularMovies()
}