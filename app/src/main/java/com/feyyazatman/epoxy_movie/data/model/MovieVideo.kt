package com.feyyazatman.epoxy_movie.data.model


import com.google.gson.annotations.SerializedName

data class MovieVideo(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<Result?>?
)