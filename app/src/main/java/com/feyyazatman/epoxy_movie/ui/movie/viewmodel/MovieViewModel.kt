package com.feyyazatman.epoxy_movie.ui.movie.viewmodel

import androidx.lifecycle.*
import com.feyyazatman.epoxy_movie.data.model.MovieResponse
import com.feyyazatman.epoxy_movie.data.repository.movie.MovieRepository
import com.feyyazatman.epoxy_movie.ui.utils.enqueue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMovies = MutableLiveData<Response<MovieResponse>?>()
    val popularMovies : LiveData<Response<MovieResponse>?> = _popularMovies

    private val _upcomingMovie = MutableLiveData<Response<MovieResponse>?>()
    val upcomingMovie : LiveData<Response<MovieResponse>?> = _upcomingMovie

    private val _inTheatre = MutableLiveData<Response<MovieResponse>?>()
    val inTheatre : LiveData<Response<MovieResponse>?> = _inTheatre

    private val _error = MutableLiveData<String?>()
    val error : LiveData<String?> = _error

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getPopularMovie().enqueue {
                onResponse =  { response -> _popularMovies.value = response }
                onFailure = { t -> _error.value = t?.message }
            }
            movieRepository.getUpcomingMovie().enqueue {
                onResponse = { response -> _upcomingMovie.value = response }
                onFailure = { t -> _error.value = t?.message }
            }
            movieRepository.getNowPlayingMovie().enqueue {
                onResponse = { response -> _inTheatre.value = response }
                onFailure = { t -> _error.value = t?.message }
            }
        }
    }
}