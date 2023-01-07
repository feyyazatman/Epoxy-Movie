package com.feyyazatman.epoxy_movie.data.model


import com.google.gson.annotations.SerializedName

data class MovieCast(
    @SerializedName("cast")
    val cast: List<Cast?>?,
    @SerializedName("crew")
    val crew: List<Crew?>?,
    @SerializedName("id")
    val id: Int?
)