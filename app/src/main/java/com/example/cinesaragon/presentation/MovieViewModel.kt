package com.example.cinesaragon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.cinesaragon.core.Resource
import com.example.cinesaragon.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MovieViewModel(private val repo: MovieRepository): ViewModel() {

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(Triple(repo.getUpcomingMovies(),repo.getPopularMovies(),repo.getTopRatedMovies())))
        } catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

    fun fetchUpcomingMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Succes(repo.getUpcomingMovies()))
        } catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }

}

class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}