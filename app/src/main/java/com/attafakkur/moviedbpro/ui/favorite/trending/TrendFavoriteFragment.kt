package com.attafakkur.moviedbpro.ui.favorite.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.attafakkur.moviedbpro.databinding.FragmentTrendFavoritBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.ui.favorite.FavoriteViewModel

class TrendFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentTrendFavoritBinding
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrendFavoritBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        populateFavoriteTrend()
    }

    private fun populateFavoriteTrend() {
        viewModel.getFavoriteTrend().observe(viewLifecycleOwner, { favMovie ->
            if (favMovie != null) {
                binding.rvFavTrend.adapter?.let { adapter ->

                    when (adapter) {
                        is TrendFavAdapter -> {
                            if (favMovie.isNullOrEmpty()) {
                                binding.rvFavTrend.visibility = View.GONE
                                binding.empty.visibility = View.VISIBLE
                            } else {
                                binding.rvFavTrend.visibility = View.VISIBLE
                                adapter.submitList(favMovie)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }

            }
        })
        binding.rvFavTrend.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = TrendFavAdapter()
        }
    }


}