package com.example.movieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.delegates.BannerShowCaseViewHolderDelegate
import com.example.movieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_banner_showcase.view.*

class BannerShowCaseViewHolder(itemView : View,private val delegate: BannerShowCaseViewHolderDelegate):RecyclerView.ViewHolder(itemView) {
    private var mMovie: MovieVO? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let   {
                delegate.onTapMovieFromBanner(it.id)
           }
        }
    }
    fun bindData(movie: MovieVO) {
        mMovie = movie
        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivBannerShowCase)

     //   itemView.tvBannerMovieName.text = movie.title
    }
}