package com.attafakkur.moviedbpro.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.databinding.ItemRelateBinding
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_MOVIES
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide

class RelateMovieAdapter(
    private val listMovies: List<MoviesEntity>
) : RecyclerView.Adapter<RelateMovieAdapter.MovieHolder>() {
    class MovieHolder(private val binding: ItemRelateBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(relate: MoviesEntity) {
            with(binding) {
                title.text = relate.title
                release.text = relate.release_date
                ratingRelate.text = itemView.resources.getString(R.string.rating_d, relate.vote_average)

                Glide.with(itemView.context)
                        .load(IMAGE_URL + relate.poster_path)
                        .centerCrop()
                        .error(R.drawable.ic_action_err)
                        .into(poster)

                itemView.setOnClickListener {
                    Intent(itemView.context, DetailMoviesActivity::class.java).apply {
                        putExtra(EXTRA_MOVIES, relate)
                        itemView.context.startActivity(this)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ItemRelateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }
    override fun getItemCount(): Int = listMovies.size

}

