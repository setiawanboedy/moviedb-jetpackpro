package com.attafakkur.moviedbpro.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.databinding.FragmentFavorite2Binding
import com.google.android.material.tabs.TabLayoutMediator

class Favorite2Fragment : Fragment() {

    private lateinit var binding: FragmentFavorite2Binding
    companion object {
        private val TAB_TITLES = intArrayOf(R.string.tbTrend, R.string.tbSearch)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavorite2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = FavoritePager2Adapter(childFragmentManager, lifecycle)
        binding.viewPager2.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }
}