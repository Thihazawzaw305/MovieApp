package com.example.movieapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.vos.MovieVO
import io.reactivex.rxjava3.core.Flowable


@Dao
interface MovieDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertMovies(movies : List<MovieVO>)
//
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertSingleMovie(movie : MovieVO)
//
//    @Query("SELECT * FROM movies")
//    fun getAllMovies() : List<MovieVO>
//
//    @Query(" SELECT * FROM movies WHERE id = :movieId")
//    fun getMovieById(movieId : Int) : MovieVO?
//
//    @Query("SELECT * FROM movies WHERE type = :type")
//    fun getMovieByType(type : String) : List<MovieVO>
//
//    @Query("DELETE FROM movies")
//    fun deleteAllMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies (movies : List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie(movie: MovieVO? )

    @Query("UPDATE movies SET wishlist = :wishlist WHERE id = :movieId")
    fun addWishlist(movieId: Int, wishlist : Boolean)

    @Query("SELECT * FROM movies WHERE wishlist = :wishlist")
    fun getAllWishListMovies(wishlist: Boolean): LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId : Int): LiveData<MovieVO?>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieByIdOneTime(movieId : Int): MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMovieByType(type: String): LiveData<List<MovieVO>>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

}