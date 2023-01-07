package com.feyyazatman.epoxy_movie.ui.tvShow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feyyazatman.epoxy_movie.data.model.TvShowResponse
import com.feyyazatman.epoxy_movie.data.repository.tvShow.TvShowRepository
import com.feyyazatman.epoxy_movie.ui.utils.enqueue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository)
    : ViewModel() {

    private val _tvShowPopular = MutableLiveData<Response<TvShowResponse>?>()
    val tvShowPopular : LiveData<Response<TvShowResponse>?> = _tvShowPopular

    private val _error = MutableLiveData<String?>()
    val error : LiveData<String?> = _error

    init {
        getPopularTvShow()
    }

    private fun getPopularTvShow() {
        viewModelScope.launch(Dispatchers.IO) {
                tvShowRepository.getPopularTvShow().enqueue {
                    onResponse =  { response -> _tvShowPopular.value = response }
                    onFailure = { t -> _error.value = t?.message }
                }
        }
    }
}