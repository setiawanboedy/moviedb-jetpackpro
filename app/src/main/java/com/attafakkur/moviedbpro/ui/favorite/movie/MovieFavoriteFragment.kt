package com.attafakkur.moviedbpro.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.attafakkur.moviedbpro.databinding.MovieFavoriteFragmentBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.ui.favorite.FavoriteViewModel
import com.attafakkur.moviedbpro.ui.movies.MoviesAdapter

class MovieFavoriteFragment : Fragment() {

    private lateinit var binding: MovieFavoriteFragmentBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieFavoriteFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        populateFavoriteMovies()
    }

    private fun populateFavoriteMovies() {
        viewModel.getFavoriteMovie().observe(viewLifecycleOwner, { favMovie ->
            if (favMovie != null) {
                binding.rvFavMovie.adapter?.let { adapter ->

                    when (adapter) {
                        is MoviesAdapter -> {
                            if (favMovie.isNullOrEmpty()) {
                                binding.rvFavMovie.visibility = GONE
                                binding.empty.visibility = VISIBLE
                            } else {
                                binding.rvFavMovie.visibility = VISIBLE
                                adapter.submitList(favMovie)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }

            }
        })
        binding.rvFavMovie.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = MoviesAdapter()
        }
    }
}