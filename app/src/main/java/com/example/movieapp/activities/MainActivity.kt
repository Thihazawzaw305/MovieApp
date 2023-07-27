package com.example.movieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.fragments.HomeFragment
import com.example.movieapp.fragments.ProfileFragment
import com.example.movieapp.fragments.SearchFragment
import com.example.movieapp.fragments.WishlistFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNavWithFragment()


    }


    // setUp bottom Nav with Fragment
    private fun setUpBottomNavWithFragment() {
        switchFragment(HomeFragment())
        bottomNav.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_Home -> {
                    switchFragment(HomeFragment())
                }

                R.id.action_Search -> {
                    switchFragment(SearchFragment())

                }

                R.id.action_Wishlist ->{
                    switchFragment(WishlistFragment())
                }

                R.id.action_Profile  -> {
                    switchFragment(ProfileFragment())
                }
            }

            true

        }
    }

    private fun switchFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.flContainer,fragment)
            .commit()
    }


}