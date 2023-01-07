package com.feyyazatman.epoxy_movie.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feyyazatman.epoxy_movie.data.model.MovieCast
import com.feyyazatman.epoxy_movie.data.model.MovieDetail
import com.feyyazatman.epoxy_movie.data.repository.movie.MovieRepository
import com.feyyazatman.epoxy_movie.ui.utils.enqueue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {


    private val _movie = MutableLiveData<Response<MovieDetail>?>()
    val movie : LiveData<Response<MovieDetail>?> = _movie

    private val _cast = MutableLiveData<Response<MovieCast>?>()
    val cast : LiveData<Response<MovieCast>?> = _cast

    private val _error = MutableLiveData<String?>()
    val error : LiveData<String?> = _error

     fun getMovie(movieId : String?) {
        viewModelScope.launch(Dispatchers.IO) {
            movieId?.let {
                movieRepository.getMovieById(movieId).enqueue {
                    onResponse =  { response -> _movie.value = response }
                    onFailure = { t -> _error.value = t?.message }
                }
                movieRepository.getMovieCredits(movieId).enqueue {
                    onResponse = { response -> _cast.value = response }
                    onFailure = { t -> _error.value = t?.message }
                }
            }
        }
    }
}