package com.attafakkur.moviedbpro.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.databinding.ActivityFavoriteBinding
import com.attafakkur.moviedbpro.ui.favorite.Favorite2Fragment
import com.attafakkur.moviedbpro.ui.favorite.FavoriteFragment

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val mFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        populateViewPagerUp()
        populateViewPagerDown()

    }

    private fun populateViewPagerDown() {
        val mSearchFragment = Favorite2Fragment()
        val fragment = mFragmentManager
            .findFragmentByTag(Favorite2Fragment::class.java.simpleName)
        if (fragment !is Favorite2Fragment) {
            mFragmentManager.beginTransaction().apply {
                add(
                    R.id.favorite2_container,
                    mSearchFragment,
                    Favorite2Fragment::class.java.simpleName
                )
                commit()
            }
        }
    }

    private fun populateViewPagerUp() {
        val mSearchFragment = FavoriteFragment()
        val fragment = mFragmentManager
            .findFragmentByTag(FavoriteFragment::class.java.simpleName)
        if (fragment !is FavoriteFragment) {
            mFragmentManager.beginTransaction().apply {
                add(
                    R.id.favorite_container,
                    mSearchFragment,
                    FavoriteFragment::class.java.simpleName
                )
                commit()
            }
        }
    }
}