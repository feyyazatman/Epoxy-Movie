package com.feyyazatman.epoxy_movie.data.repository.movie

import com.feyyazatman.epoxy_movie.data.model.MovieCast
import com.feyyazatman.epoxy_movie.data.model.MovieDetail
import com.feyyazatman.epoxy_movie.data.model.MovieResponse
import com.feyyazatman.epoxy_movie.data.model.MovieVideo
import retrofit2.Call
import retrofit2.http.Path

interface MovieRepository {

    suspend fun getPopularMovie() : Call<MovieResponse>

    suspend fun getUpcomingMovie() : Call<MovieResponse>

    suspend fun getNowPlayingMovie() : Call<MovieResponse>

    suspend fun getMovieById(movieId : String) : Call<MovieDetail>

    suspend fun getMovieVideo(id : String) : Call<MovieVideo>

    suspend fun getMovieCredits(@Path("id") id: String): Call<MovieCast>

}