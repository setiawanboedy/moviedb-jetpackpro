package com.attafakkur.moviedbpro.ui.favorite.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.attafakkur.moviedbpro.databinding.FragmentSearchFavoriteBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.ui.favorite.FavoriteViewModel
import com.attafakkur.moviedbpro.ui.search.SearchAdapter


class SearchFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentSearchFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        populateFavoriteSearch()
    }

    private fun populateFavoriteSearch() {
        viewModel.getFavoriteSearch().observe(viewLifecycleOwner, { favMovie ->
            if (favMovie != null) {
                binding.rvFavSearch.adapter?.let { adapter ->

                    when (adapter) {
                        is SearchAdapter -> {
                            if (favMovie.isNullOrEmpty()) {
                                binding.rvFavSearch.visibility = View.GONE
                                binding.empty.visibility = View.VISIBLE
                            } else {
                                binding.rvFavSearch.visibility = View.VISIBLE
                                adapter.submitList(favMovie)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }

            }
        })
        binding.rvFavSearch.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = SearchAdapter()
        }

    }
}