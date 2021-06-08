package com.attafakkur.moviedbpro.ui.tvshows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.databinding.ItemListBinding
import com.attafakkur.moviedbpro.ui.detail.DetailTVActivity
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_TVSHOWS
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide

class TvShowAdapter :
    PagedListAdapter<TvShowsEntity, TvShowAdapter.TvHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowsEntity>() {
            override fun areItemsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowsEntity, newItem: TvShowsEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    class TvHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShowsEntity) {
            with(binding) {
                title.text = tvShow.name
                release.text = tvShow.first_air_date
                ratingList.text = itemView.resources.getString(R.string.rating_d, tvShow.vote_average)

                Glide.with(itemView.context)
                        .load(IMAGE_URL + tvShow.poster_path)
                        .centerCrop()
                        .error(R.drawable.ic_action_err)
                        .into(poster)

                itemView.setOnClickListener {
                    Intent(itemView.context, DetailTVActivity::class.java).apply {
                        putExtra(EXTRA_TVSHOWS, tvShow)
                        itemView.context.startActivity(this)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvHolder(binding)
    }

    override fun onBindViewHolder(holder: TvHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }


}