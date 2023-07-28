package com.example.movieapp.data.models

import androidx.lifecycle.LiveData
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.MovieVO

interface MovieModel {
    fun getUpComingMovies(
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularMovies(
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?
    fun getMovieDetails(
        movieId : String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>?

    fun getActorByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )
   fun addWishlist(movieId : Int, wishList : Boolean)
    fun getAllWishListMovies(wishlist: Boolean): LiveData<List<MovieVO>>?
}