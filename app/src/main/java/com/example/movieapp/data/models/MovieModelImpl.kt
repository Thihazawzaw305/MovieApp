package com.example.movieapp.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.data.vos.POPULAR
import com.example.movieapp.data.vos.UP_COMING
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl: MovieModel, BaseModel() {
    @SuppressLint("CheckResult")
    override fun getUpComingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {

        mMovieApi.getUpComingMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach{movie -> movie.type = UP_COMING}
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage?:"")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = UP_COMING)
    }
    @SuppressLint("CheckResult")
    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {

        mMovieApi.getPopularMovies(page = 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach{movie -> movie.type = POPULAR}
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage?:"")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)
    }

    override fun getMovieDetails(
        movieId: String,

        onFailure: (String) -> Unit
    ): LiveData<MovieVO?>? {

        mMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val movieFromDatabaseToSync =
                    mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = movieFromDatabaseToSync?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)
            },{
                onFailure(it.localizedMessage?:"")
            })
        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }


    @SuppressLint("CheckResult")
    override fun getActorByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getActorByMovie(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.cast?: listOf())
            },{onFailure(it.localizedMessage ?: "")})
    }

    override fun addWishlist(movieId: Int, wishList: Boolean) {
      mMovieDatabase?.movieDao()?.addWishlist(movieId,wishList)
    }

    override fun getAllWishListMovies(wishlist: Boolean): LiveData<List<MovieVO>>? {
     return mMovieDatabase?.movieDao()?.getAllWishListMovies(wishlist)
    }


}