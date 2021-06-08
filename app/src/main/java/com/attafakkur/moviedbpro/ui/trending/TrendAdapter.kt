package com.attafakkur.moviedbpro.ui.trending

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.databinding.ListTrendingBinding
import com.attafakkur.moviedbpro.ui.detail.DetailTrendingActivity
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_TRENDING
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide

class TrendAdapter :
    PagedListAdapter<TrendEntity, TrendAdapter.TrendHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrendEntity>() {
            override fun areItemsTheSame(oldItem: TrendEntity, newItem: TrendEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TrendEntity, newItem: TrendEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    class TrendHolder(private val binding: ListTrendingBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(trend: TrendEntity) {
            with(binding) {
                titleReco.text = trend.title
                ratingTrend.text = itemView.resources.getString(R.string.rating_d, trend.vote_average)

                Glide.with(itemView.context)
                        .load(IMAGE_URL + trend.poster_path)
                        .centerCrop()
                        .error(R.drawable.ic_action_err)
                        .into(posterReco)

                itemView.setOnClickListener {
                        Intent(itemView.context, DetailTrendingActivity::class.java).apply {
                            putExtra(EXTRA_TRENDING, trend)
                            itemView.context.startActivity(this)
                        }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendHolder {
        val binding = ListTrendingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendHolder, position: Int) {
        val trend = getItem(position)
        if (trend != null) {
            holder.bind(trend)
        }
    }
}