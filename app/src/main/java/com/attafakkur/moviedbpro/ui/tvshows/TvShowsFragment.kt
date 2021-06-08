package com.attafakkur.moviedbpro.ui.tvshows

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.attafakkur.moviedbpro.databinding.TvShowsFragmentBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.utils.hidePB
import com.attafakkur.moviedbpro.utils.refresh
import com.attafakkur.moviedbpro.utils.showPB
import com.attafakkur.moviedbpro.utils.snack
import com.attafakkur.moviedbpro.vo.Status

class TvShowsFragment : Fragment() {

    private lateinit var viewModel: TvShowsViewModel
    private lateinit var binding: TvShowsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TvShowsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDataTvShows()
        refresh(binding.swipeTv) {
            showDataTvShows()
        }
    }
    private fun showDataTvShows() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]
        viewModel.getTvData().observe(viewLifecycleOwner, { tvShow ->
            if (tvShow != null){
                when(tvShow.status){
                    Status.LOADING -> {
                        binding.pbTv.showPB()
                    }
                    Status.SUCCESS -> {
                        binding.pbTv.hidePB()
                        binding.rvTvShows.adapter.let { adapter ->
                            when(adapter){
                                is TvShowAdapter -> adapter.submitList(tvShow.data)
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.pbTv.hidePB()
                        tvShow.message?.let { binding.root.snack(it) }
                    }
                }
            }
        })
        binding.rvTvShows.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = TvShowAdapter()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            binding.rvTvShows.layoutManager = GridLayoutManager(context, 4)
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            binding.rvTvShows.layoutManager = GridLayoutManager(context, 2)
        }
    }

}