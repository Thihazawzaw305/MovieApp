package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.movieapp.R
import com.example.movieapp.activities.MovieDetailActivity
import com.example.movieapp.adapters.BannerShowCaseAdapter
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.delegates.BannerShowCaseViewHolderDelegate
import com.example.movieapp.delegates.MovieViewHolderDelegate
import com.example.movieapp.viewpods.MovieListViewPod
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), BannerShowCaseViewHolderDelegate, MovieViewHolderDelegate {
    private lateinit var mBannerShowCaseAdapter: BannerShowCaseAdapter
    private lateinit var mUpComingMovieListViewPod: MovieListViewPod
    private lateinit var mPopularMovieListViewPod: MovieListViewPod
    private val mAppCompatActivity = AppCompatActivity()

    //model
    private val mMovieModel: MovieModel = MovieModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBanner()
        setUpViewPod()
        requestData()

    }

    //setUpBanner
    private fun setUpBanner() {
        mBannerShowCaseAdapter = BannerShowCaseAdapter(this)
        viewPagerBanner.adapter = mBannerShowCaseAdapter
        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    private fun setUpViewPod() {

        mUpComingMovieListViewPod = vpUpComingMovieList as MovieListViewPod
        mUpComingMovieListViewPod.setUpMovieListViewPod(this)
        mPopularMovieListViewPod = vpPopularMovieList as MovieListViewPod
        mPopularMovieListViewPod.setUpMovieListViewPod(this)


    }

    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(requireContext(), movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(requireContext(), movieId))
    }

    private fun requestData() {
        mMovieModel.getUpComingMovies {
            showError(it)
        }?.observe(viewLifecycleOwner, Observer {
            mBannerShowCaseAdapter.setNewData(it)
            mUpComingMovieListViewPod.setData(it)
        })

        mMovieModel.getPopularMovies {
            showError(it)
        }?.observe(viewLifecycleOwner, Observer {
            mPopularMovieListViewPod.setData(it)
        })
    }

    private fun showError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }
}