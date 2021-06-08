package com.attafakkur.moviedbpro.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.databinding.ActivityHomeBinding
import com.attafakkur.moviedbpro.ui.trending.TrendFragment
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_SEARCH
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityHomeBinding
    private val mHomeFragment = TrendFragment()
    private val mFragmentManager = supportFragmentManager

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.tbmovies, R.string.tbtvshows)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        binding.favorite.setOnClickListener{
            startActivity(Intent(this, FavoriteActivity::class.java))
        }
        binding.searchMovie.setOnQueryTextListener(this)

        val fragment = mFragmentManager
                .findFragmentByTag(TrendFragment::class.java.simpleName)
        if (fragment !is TrendFragment) {
            mFragmentManager.beginTransaction().apply {
                replace(R.id.fl_recommend, mHomeFragment, TrendFragment::class.java.simpleName)
                disallowAddToBackStack()
                commit()
            }
        }

        val sectionsPagerAdapter = MoviePagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Intent(this, SearchActivity::class.java).apply {
            putExtra(EXTRA_SEARCH, query)
            this@HomeActivity.startActivity(this)
        }
            return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

}