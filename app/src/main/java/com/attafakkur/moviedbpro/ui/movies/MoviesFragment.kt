package com.attafakkur.moviedbpro.ui.movies

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.attafakkur.moviedbpro.databinding.MoviesFragmentBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.utils.hidePB
import com.attafakkur.moviedbpro.utils.refresh
import com.attafakkur.moviedbpro.utils.showPB
import com.attafakkur.moviedbpro.utils.snack
import com.attafakkur.moviedbpro.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding: MoviesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MoviesFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMovies.setHasFixedSize(true)

        showDataMovies()
        refresh(binding.swipeMovie) {
            showDataMovies()
        }
    }

    private fun showDataMovies() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
        viewModel.getMoviesData().observe(viewLifecycleOwner, { movies ->
            if (movies != null){
                when(movies.status){
                    Status.LOADING -> {
                        binding.pbMovie.showPB()
                    }
                    Status.SUCCESS -> {
                        binding.pbMovie.hidePB()
                        binding.rvMovies.adapter.let { adapter ->
                        when(adapter){
                            is MoviesAdapter -> adapter.submitList(movies.data)
                        }
                        }
                    }
                    Status.ERROR -> {
                        binding.pbMovie.hidePB()
                        movies.message?.let { binding.root.snack(it) }
                    }
                }
            }

        })
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = MoviesAdapter()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvMovies.layoutManager = GridLayoutManager(context, 4)
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.rvMovies.layoutManager = GridLayoutManager(context, 2)
        }
    }

}