package com.attafakkur.moviedbpro.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.attafakkur.moviedbpro.ui.favorite.movie.MovieFavoriteFragment
import com.attafakkur.moviedbpro.ui.favorite.tvshow.TvFavoriteFragment

class FavoritePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFavoriteFragment()
            1 -> fragment = TvFavoriteFragment()
        }
        return fragment as Fragment
    }
}