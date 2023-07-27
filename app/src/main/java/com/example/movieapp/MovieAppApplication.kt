package com.example.movieapp

import android.app.Application
import com.example.movieapp.data.models.MovieModelImpl

class MovieAppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)

    }
}