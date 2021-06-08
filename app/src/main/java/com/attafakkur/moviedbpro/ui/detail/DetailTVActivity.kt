package com.attafakkur.moviedbpro.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.databinding.ActivityDetailMTvBinding
import com.attafakkur.moviedbpro.di.ViewModelFactory
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_TVSHOWS
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.attafakkur.moviedbpro.utils.hidePB
import com.attafakkur.moviedbpro.utils.showPB
import com.attafakkur.moviedbpro.utils.snack
import com.attafakkur.moviedbpro.vo.Resource
import com.attafakkur.moviedbpro.vo.Status
import com.bumptech.glide.Glide
import java.util.*

class DetailTVActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailMTvBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val getTVParcelable = intent.getParcelableExtra<TvShowsEntity>(EXTRA_TVSHOWS) as TvShowsEntity
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        binding.pbDetail.showPB()

        with(getTVParcelable){
            populateTvShows(viewModel.getDetailTv(id))
            populateRelateTv(viewModel.getRelateDataTv(id))
            }

        binding.backDetailBlack.setOnClickListener(this)
        binding.backDetailTv.setOnClickListener(this)
    }

    private fun populateRelateTv(relateDataTv: LiveData<Resource<PagedList<TvShowsEntity>>>) {
            relateDataTv.observe(this@DetailTVActivity, { relate ->
                if (relate != null){
                    when(relate.status){
                        Status.LOADING -> {
                            binding.pbDetail.showPB()
                        }
                        Status.SUCCESS -> {
                            binding.pbDetail.hidePB()
                            binding.relateRecycler.adapter =
                                relate.data?.let { RelateTvAdapter(it) }
                        }
                        Status.ERROR -> {
                            binding.pbDetail.hidePB()
                            relate.message?.let { binding.root.snack(it) }
                        }
                    }
                }
            })
            binding.relateRecycler.layoutManager = LinearLayoutManager(
                    this@DetailTVActivity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun populateTvShows(detailTvShows: LiveData<Resource<TvShowsEntity>>) {
        detailTvShows.observe(this, { detail ->
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
                            .into(binding.backDropTv)

                        with(binding){
                            detailTitleTv.text = detail.data?.name ?: ""
                            languageDetailTv.text = resources.getString(R.string.language,
                                detail.data?.original_language
                            )
                            dateDetailTv.text = resources.getString(R.string.release,
                                detail.data?.first_air_date
                            )
                            detailRatingTv.text = resources.getString(R.string.duration_s,
                                detail.data?.vote_average
                            )
                            overviewTv.text = detail.data?.overview ?: ""
                            ratingBarTv.rating = detail.data?.vote_average?.toFloat() ?: 0f
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
    private fun setFavorite(data: TvShowsEntity?) {
        if (data != null) {
            viewModel.setFavoriteTv(data)
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