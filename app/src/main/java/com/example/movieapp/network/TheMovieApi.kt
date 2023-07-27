package com.example.movieapp.network

import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.network.responses.GetActorByMovieResponse
import com.example.movieapp.network.responses.MovieListResponse
import com.example.movieapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_UP_COMING_MOVIES)
    fun getUpComingMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ): Observable<MovieListResponse>

    @GET("$API_GET_MOVIE_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey : String = MOVIE_API_KEY,
    ):Observable<MovieVO>

    @GET("$API_GET_ACTOR_BY_MOVIE/{movie_id}/credits")
    fun getActorByMovie(
        @Path("movie_id") movieId : String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ):Observable<GetActorByMovieResponse>


}