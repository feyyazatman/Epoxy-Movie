package com.feyyazatman.epoxy_movie.data.repository.movie

import com.feyyazatman.epoxy_movie.data.model.MovieCast
import com.feyyazatman.epoxy_movie.data.model.MovieDetail
import com.feyyazatman.epoxy_movie.data.model.MovieResponse
import com.feyyazatman.epoxy_movie.data.model.MovieVideo
import com.feyyazatman.epoxy_movie.data.remote.api.ApiService
import retrofit2.Call

class MovieRepositoryImpl constructor(private val apiService: ApiService) : MovieRepository {

    override suspend fun getPopularMovie(): Call<MovieResponse> {
        return apiService.getPopularMovie()
    }

    override suspend fun getUpcomingMovie(): Call<MovieResponse> {
        return apiService.getUpcomingMovie()
    }

    override suspend fun getNowPlayingMovie(): Call<MovieResponse> {
        return apiService.getNowPlayingMovie()
    }

    override suspend fun getMovieById(movieId: String): Call<MovieDetail> {
        return apiService.getMovieById(movieId)
    }

    override suspend fun getMovieVideo(id: String): Call<MovieVideo> {
        return apiService.getMovieVideo(id)
    }

    override suspend fun getMovieCredits(id: String): Call<MovieCast> {
        return apiService.getMovieCredits(id)
    }
}