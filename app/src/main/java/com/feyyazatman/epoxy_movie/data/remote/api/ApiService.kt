package com.feyyazatman.epoxy_movie.data.remote.api

import com.feyyazatman.epoxy_movie.data.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular")
    fun getPopularMovie() : Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie() : Call<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovie() : Call<MovieResponse>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") movieId : String) : Call<MovieDetail>

    @GET("movie/{id}/videos")
    fun getMovieVideo(@Path("id") id: String): Call<MovieVideo>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: String): Call<MovieCast>

    @GET("tv/popular")
    fun getPopularTvShow() : Call<TvShowResponse>
}