package com.attafakkur.moviedbpro.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.databinding.ActivitySearchBinding
import com.attafakkur.moviedbpro.ui.search.SearchFragment
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_SEARCH

class SearchActivity : AppCompatActivity(){
    private val mFragmentManager = supportFragmentManager
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        val query = intent.getStringExtra(EXTRA_SEARCH)

        val mBundle = Bundle()
        val mSearchFragment = SearchFragment()
        val fragment = mFragmentManager
            .findFragmentByTag(SearchFragment::class.java.simpleName)
        if (fragment !is SearchFragment) {
            mBundle.putString(EXTRA_SEARCH, query)
            mSearchFragment.arguments = mBundle

            mFragmentManager.beginTransaction().apply {
                add(
                    R.id.search_container,
                    mSearchFragment,
                    SearchFragment::class.java.simpleName
                )
                commit()
            }
        }


    }

}