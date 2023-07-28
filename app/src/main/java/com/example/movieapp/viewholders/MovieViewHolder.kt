package com.example.movieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.delegates.MovieViewHolderDelegate
import com.example.movieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_banner_showcase.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieViewHolder(itemView: View, private val delegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var mMovie: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                delegate.onTapMovie(it.id)
            }

            itemView.btnWishlistFromMovie.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked)
                    mMovie?.let {
                        it.wishlist = true
                    }
                else
                    mMovie?.let {
                        it.wishlist = false
                    }

            }
        }

    }


    fun bindData(movie: MovieVO) {
        mMovie = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovieImage)
        if (movie.wishlist) {
            itemView.btnWishlistFromMovie.isChecked
        }
        itemView.tvMovieName.text = movie.title
        itemView.tvMovieRating.text = movie.voteAverage.toString()
        itemView.rbrMovieRating.rating = movie.getRatingBaseOnFiveStars()
    }


}