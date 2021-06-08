package com.attafakkur.moviedbpro.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.attafakkur.moviedbpro.databinding.TvFavoriteFragmentBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.ui.favorite.FavoriteViewModel
import com.attafakkur.moviedbpro.ui.tvshows.TvShowAdapter

class TvFavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: TvFavoriteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TvFavoriteFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        populateTvFavorite()
    }

    private fun populateTvFavorite() {
        viewModel.getFavoriteTv().observe(viewLifecycleOwner, { favTv ->
            if (favTv != null) {
                binding.rvFavTv.adapter?.let { adapter ->

                    when (adapter) {
                        is TvShowAdapter -> {
                            if (favTv.isNullOrEmpty()) {
                                binding.rvFavTv.visibility = View.GONE
                                binding.empty.visibility = View.VISIBLE
                            } else {
                                binding.rvFavTv.visibility = View.VISIBLE
                                adapter.submitList(favTv)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }

            }
        })
        binding.rvFavTv.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = TvShowAdapter()
        }
    }

}