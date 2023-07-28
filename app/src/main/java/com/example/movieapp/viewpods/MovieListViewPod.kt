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
import com.example.movieapp.delegates.WishlistDelegate

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private lateinit var mMovieListAdapter: MovieListAdapter
   private lateinit var mDelegate : MovieViewHolderDelegate
   private lateinit var wishlistDelegate: WishlistDelegate


    fun setUpMovieListViewPod(delegate : MovieViewHolderDelegate, wishlistDelegate: WishlistDelegate){
        setDelegate(delegate, wishlistDelegate)
        setUpRecyclerView()
    }

        fun setData(popularMovieList : List<MovieVO>){
        mMovieListAdapter.setNewData(popularMovieList)
    }

    private fun setDelegate(delegate : MovieViewHolderDelegate, wishlistDelegate: WishlistDelegate){
        this.mDelegate = delegate
        this.wishlistDelegate = wishlistDelegate

    }

     fun setUpRecyclerView() {
        mMovieListAdapter = MovieListAdapter(mDelegate,wishlistDelegate)
        val rvMovieList = findViewById<RecyclerView>(R.id.rvMovieList)
        rvMovieList.adapter = mMovieListAdapter
        rvMovieList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )

    }
}