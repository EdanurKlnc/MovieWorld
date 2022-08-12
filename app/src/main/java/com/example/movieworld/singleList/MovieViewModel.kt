package com.example.movieworld.singleList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.movieworld.model.MovieResponse
import com.example.movieworld.network.NetworkSingleton
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel : ViewModel() {

    private val _moviesResponse = MutableLiveData<MovieResponse>()
    var movieResponse: LiveData<MovieResponse> = _moviesResponse
    init {
        getMovies()
    }

    private fun getMovies() {

        viewModelScope.launch {
            try {
                val movieResponse: MovieResponse = NetworkSingleton.service.getPopularMovies()
                _moviesResponse.postValue(movieResponse)

            } catch (e: Throwable) {
                Timber.e(e)
            }

        }
    }


}
