package com.example.movieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieListAdapter
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.delegates.MovieViewHolderDelegate

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var mMovieListAdapter: MovieListAdapter
   private lateinit var mDelegate : MovieViewHolderDelegate


    fun setUpMovieListViewPod(delegate : MovieViewHolderDelegate){
        setDelegate(delegate)
        setUpRecyclerView()
    }

        fun setData(popularMovieList : List<MovieVO>){
        mMovieListAdapter.setNewData(popularMovieList)
    }

    private fun setDelegate(delegate : MovieViewHolderDelegate){
        this.mDelegate = delegate

    }

     fun setUpRecyclerView() {
        mMovieListAdapter = MovieListAdapter(mDelegate)
        val rvMovieList = findViewById<RecyclerView>(R.id.rvMovieList)
        rvMovieList.adapter = mMovieListAdapter
        rvMovieList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )

    }
}