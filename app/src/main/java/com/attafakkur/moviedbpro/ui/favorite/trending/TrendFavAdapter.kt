package com.attafakkur.moviedbpro.ui.favorite.trending

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.databinding.ItemListBinding
import com.attafakkur.moviedbpro.ui.detail.DetailTrendingActivity
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_TRENDING
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide

class TrendFavAdapter :
    PagedListAdapter<TrendEntity, TrendFavAdapter.MovieHolder>(DIFF_CALLBACK) {

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
    class MovieHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: TrendEntity) {
            with(binding) {
                title.text = movie.title
                release.text = movie.release_date
                ratingList.text = itemView.resources.getString(R.string.rating_d, movie.vote_average)

                Glide.with(itemView.context)
                        .load(IMAGE_URL + movie.poster_path)
                        .centerCrop()
                        .error(R.drawable.ic_action_err)
                        .into(poster)

                itemView.setOnClickListener {
                    Intent(itemView.context, DetailTrendingActivity::class.java).apply {
                        putExtra(EXTRA_TRENDING, movie)
                        itemView.context.startActivity(this)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}

