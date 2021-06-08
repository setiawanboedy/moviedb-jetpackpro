package com.attafakkur.moviedbpro.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.attafakkur.moviedbpro.ui.movies.MoviesFragment
import com.attafakkur.moviedbpro.ui.tvshows.TvShowsFragment

class MoviePagerAdapter(fM: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fM, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MoviesFragment()
            1 -> fragment = TvShowsFragment()
        }
        return fragment as Fragment
    }
}