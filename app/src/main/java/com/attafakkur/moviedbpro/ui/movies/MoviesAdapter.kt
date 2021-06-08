package com.attafakkur.moviedbpro.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.databinding.ItemListBinding
import com.attafakkur.moviedbpro.ui.detail.DetailMoviesActivity
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_MOVIES
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide

class MoviesAdapter :
    PagedListAdapter<MoviesEntity, MoviesAdapter.MovieHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    class MovieHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MoviesEntity) {
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
                    Intent(itemView.context, DetailMoviesActivity::class.java).apply {
                        putExtra(EXTRA_MOVIES, movie)
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

