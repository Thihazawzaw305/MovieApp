package com.example.movieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.mvvm.MovieDetailsViewModel
import com.example.movieapp.utils.IMAGE_BASE_URL
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {
    private val mMovieModel: MovieModel = MovieModelImpl
    private lateinit var mViewModel : MovieDetailsViewModel
    companion object {

        const val MOVIE_ID = "MOVIE_ID"

        fun newIntent(context: Context, movieId : Int ): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setUpListener()
        val movieId = intent?.getIntExtra(MOVIE_ID, 0)

//        if (movieId != null) {
//            requestData(movieId)
//        }
        movieId?.let {
            setUpViewModel(it)
        }
        observeData()
    }

    private fun setUpViewModel(movieId: Int){
        mViewModel =ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        mViewModel.getInitialData(movieId)
    }

    private fun observeData(){
        mViewModel.movieDetailsLiveData?.observe(this){
            it?.let { movie -> bindData(movie) }
        }
        mViewModel.castLiveData.observe(this){
            tvCast.text = "${it[0].name.toString()},${it[1].name.toString()},${it[2].name.toString()},${it[3].name.toString()}"

        }
    }

//    private fun requestData(movieId : Int){
//        mMovieModel.getMovieDetails(movieId = movieId.toString(), onFailure = {
//            Snackbar.make(window.decorView,it,Snackbar.LENGTH_SHORT).show()
//        })?.observe(this,
//            Observer {
//                it?.let { movieDetails -> bindData(movieDetails) }
//            })
//
//
//
//        mMovieModel.getActorByMovie(movieId = movieId.toString(),
//        onSuccess = {
//           tvCast.text = "${it[0].name.toString()},${it[1].name.toString()},${it[2].name.toString()},${it[3].name.toString()}"
//        },
//        onFailure = {
//            Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
//        })
//    }

    private fun bindData(movie : MovieVO){
        Glide.with(this)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(ivMovieDetail)
        tvMovieDetailTitle.text = movie.title
       bindGenre(movie,movie.genres?: listOf())
        tvStoryLine.text = movie.overview.toString()
        tvReleaseDate.text = movie.releaseDate.toString()
        rbrRatingMovieDetail.rating = movie.getRatingBaseOnFiveStars()
        tvRating.text =(movie.voteAverage ?: 0.0f).toString()
        tvRatingCount.text ="${movie.voteCount} rating available"




    }

    private fun bindGenre(movie : MovieVO, genre : List<GenreVO>){

        movie.genres?.count()?.let {
            tvGenre.text = "${genre.firstOrNull()?.name?:""},${genre.getOrNull(1)?.name?:""},${genre.getOrNull(2)?.name?:""}"
            tvMovieDetailGenre.text = "${genre.firstOrNull()?.name?:""},${genre.getOrNull(1)?.name?:""},${genre.getOrNull(2)?.name?:""}"

        }

    }

    private fun setUpListener(){
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}