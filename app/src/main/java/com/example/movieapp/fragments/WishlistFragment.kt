package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieListAdapter
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.delegates.MovieViewHolderDelegate
import com.example.movieapp.persistence.MovieDatabase
import kotlinx.android.synthetic.main.fragment_wishlist.*

class WishlistFragment : Fragment() , MovieViewHolderDelegate {
    private lateinit var mMovieAdapter : MovieListAdapter
    private val mMovieModel : MovieModel = MovieModelImpl
    private var wishlistmovie : List<MovieVO> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getData()
    }
    private fun setUpRecyclerView(){
        mMovieAdapter = MovieListAdapter(this)
        rvWishlist.adapter = mMovieAdapter
        rvWishlist.layoutManager = GridLayoutManager(context,2)
    }

    private fun getData(){
        mMovieModel.getAllMovies()?.observe(viewLifecycleOwner){
           mMovieAdapter.setNewData(it)

        }
    }

    override fun onTapMovie(movieId: Int) {

    }
}