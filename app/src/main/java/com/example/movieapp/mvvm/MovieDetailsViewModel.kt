package com.example.movieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.MovieVO


class MovieDetailsViewModel: ViewModel() {
    // Model
    private val mMovieModel = MovieModelImpl

    // LiveData
    var movieDetailsLiveData: LiveData<MovieVO?>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(movieId : Int){
        movieDetailsLiveData =
            mMovieModel.getMovieDetails(movieId = movieId.toString()){mErrorLiveData.postValue(it)}

        mMovieModel.getActorByMovie(movieId = movieId.toString(), onSuccess = {
            castLiveData.postValue(it?: listOf())
        }, onFailure = {
            mErrorLiveData.postValue(it)
        })
    }



}