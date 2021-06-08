package com.attafakkur.moviedbpro.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.attafakkur.moviedbpro.databinding.TrendFragmentBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.utils.hidePB
import com.attafakkur.moviedbpro.utils.showPB
import com.attafakkur.moviedbpro.utils.snack
import com.attafakkur.moviedbpro.vo.Status

class TrendFragment : Fragment() {

    private lateinit var viewModel: TrendViewModel
    private lateinit var binding: TrendFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = TrendFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(TrendViewModel::class.java)

        populateTrending()
    }

    private fun populateTrending() {
        viewModel.getTrendData().observe(viewLifecycleOwner, {trending ->
            if (trending != null){
                when(trending.status){
                    Status.LOADING -> {
                        binding.pbTrend.showPB()
                    }
                    Status.SUCCESS -> {
                        binding.pbTrend.hidePB()
                        binding.rvTrend.adapter.let { adapter ->
                            when (adapter) {
                                is TrendAdapter -> adapter.submitList(trending.data)
                            }
                        }
                    }
                    Status.ERROR -> {
                        binding.pbTrend.hidePB()
                        trending.message?.let { binding.root.snack(it) }
                    }
                }
            }
        })

        binding.rvTrend.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = TrendAdapter()
        }
    }

}