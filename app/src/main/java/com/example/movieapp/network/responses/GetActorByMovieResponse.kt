package com.example.movieapp.network.responses

import com.example.movieapp.data.vos.ActorVO
import com.google.gson.annotations.SerializedName


data class GetActorByMovieResponse (

    @SerializedName("cast")
    val cast : List<ActorVO>?,


)