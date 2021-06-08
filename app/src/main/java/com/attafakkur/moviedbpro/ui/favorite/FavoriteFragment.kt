package com.attafakkur.moviedbpro.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    companion object {
        private val TAB_TITLES = intArrayOf(R.string.tbmovies, R.string.tbtvshows)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = FavoritePagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}