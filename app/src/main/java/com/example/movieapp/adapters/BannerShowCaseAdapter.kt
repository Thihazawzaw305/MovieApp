package com.example.movieapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.delegates.BannerShowCaseViewHolderDelegate
import com.example.movieapp.viewholders.BannerShowCaseViewHolder

class BannerShowCaseAdapter(private val delegate: BannerShowCaseViewHolderDelegate):RecyclerView.Adapter<BannerShowCaseViewHolder>(){
    private var mMovieList: List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerShowCaseViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_banner_showcase,parent,false)
        return BannerShowCaseViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: BannerShowCaseViewHolder, position: Int) {
        if (mMovieList.isNotEmpty())
            holder.bindData(mMovieList[position])
    }

    override fun getItemCount(): Int {
        return if(mMovieList.count()>5)
            5
        else
            mMovieList.count()
    }

    @Suppress("NotifyDataSetChanged")
    fun setNewData(movieList: List<MovieVO>) {
        mMovieList = movieList
        notifyDataSetChanged()
    }}