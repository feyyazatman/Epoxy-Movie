package com.feyyazatman.epoxy_movie.data.repository.tvShow

import com.feyyazatman.epoxy_movie.data.model.TvShowResponse
import com.feyyazatman.epoxy_movie.data.remote.api.ApiService
import retrofit2.Call

class TvShowRepositoryImpl constructor(private val apiService: ApiService) : TvShowRepository{

    override suspend fun getPopularTvShow(): Call<TvShowResponse> {
        return apiService.getPopularTvShow()
    }
}