package com.example.movieapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.data.vos.MovieVO

class MainViewModel: ViewModel() {
    //model
    private val mMovieModel = MovieModelImpl
    //live data
    var upComingMoviesLiveData : LiveData<List<MovieVO>>? = null
    var popularMovieLiveData : LiveData<List<MovieVO>>? = null
    val mErrorLiveData = MutableLiveData<String>()


    fun getInitialData(){
        upComingMoviesLiveData = mMovieModel.getUpComingMovies { mErrorLiveData.postValue(it) }
        popularMovieLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }



}}