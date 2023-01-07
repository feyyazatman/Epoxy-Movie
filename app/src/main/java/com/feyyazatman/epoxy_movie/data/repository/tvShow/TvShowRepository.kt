package com.feyyazatman.epoxy_movie.data.repository.tvShow

import com.feyyazatman.epoxy_movie.data.model.TvShowResponse
import retrofit2.Call

interface TvShowRepository {

    suspend fun getPopularTvShow() : Call<TvShowResponse>
}