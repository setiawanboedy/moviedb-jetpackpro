package com.attafakkur.moviedbpro.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.databinding.ActivityDetailMTBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_MOVIES
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.attafakkur.moviedbpro.utils.hidePB
import com.attafakkur.moviedbpro.utils.showPB
import com.attafakkur.moviedbpro.utils.snack
import com.attafakkur.moviedbpro.vo.Resource
import com.attafakkur.moviedbpro.vo.Status
import com.bumptech.glide.Glide

class DetailMoviesActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailMTBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMTBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        val getMovieParcel = intent.getParcelableExtra<MoviesEntity>(EXTRA_MOVIES) as MoviesEntity

        binding.pbDetail.showPB()

        with(getMovieParcel){
            populateMovies(viewModel.getDetailMovies(id))
            populateRelate(viewModel.getRelateDataMovie(id))
        }

        binding.backDetail.setOnClickListener(this)
        binding.backDetailBlack.setOnClickListener(this)
    }

    private fun populateRelate(relateData: LiveData<Resource<PagedList<MoviesEntity>>>) {
        relateData.observe(this, { relate ->
            if (relate != null){
                when(relate.status){
                    Status.LOADING -> {
                        binding.pbDetail.showPB()
                    }
                    Status.SUCCESS -> {
                        binding.pbDetail.hidePB()
                        binding.relateRecycler.adapter =
                            relate.data?.let { RelateMovieAdapter(it) }
                    }
                    Status.ERROR -> {
                        binding.pbDetail.hidePB()
                        relate.message?.let { binding.root.snack(it) }
                    }
                }
            }
        })
        binding.relateRecycler.layoutManager = LinearLayoutManager(
                this@DetailMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun populateMovies(detailMovies: LiveData<Resource<MoviesEntity>>) {
        detailMovies.observe(this, { detail ->
            if (detail != null){
                when(detail.status){
                    Status.LOADING -> {
                        binding.pbDetail.showPB()
                    }
                    Status.SUCCESS -> {
                        binding.pbDetail.hidePB()
                        Glide.with(this)
                            .load(IMAGE_URL + (detail.data?.backdrop_path ?: ""))
                            .centerCrop()
                            .into(binding.backDrop)

                            with(binding){
                                detailTitle.text = detail.data?.title ?: ""
                                languageDetail.text = resources.getString(R.string.language,
                                    detail.data?.original_language
                                )
                                dateDetail.text = resources.getString(R.string.release,
                                    detail.data?.release_date
                                )
                                detailRating.text = resources.getString(R.string.duration_s,
                                    detail.data?.vote_average
                                )
                                overview.text = detail.data?.overview ?: ""
                                ratingBar.rating = detail.data?.vote_average?.toFloat() ?: 0f
                        }
                        val state = detail.data?.isFavorite
                        if (state != null) {
                            setFavoriteState(state)
                        }

                        binding.starBorder.setOnClickListener { setFavorite(detail.data) }
                    }
                    Status.ERROR -> {
                        binding.pbDetail.hidePB()
                        detail.message?.let { binding.root.snack(it) }
                    }
                }
            }
        })
    }

    private fun setFavorite(data: MoviesEntity?) {
        if (data != null) {
            viewModel.setFavoriteMovie(data)
        }
    }

    private fun setFavoriteState(isFavorite: Boolean) {
        if (isFavorite){
            binding.starBorder.setImageResource(R.drawable.ic_baseline_star)
        }else{
            binding.starBorder.setImageResource(R.drawable.ic_baseline_star_border)
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.back_detail -> {finish()}
            R.id.back_detail_black -> {finish()}
        }
    }
}