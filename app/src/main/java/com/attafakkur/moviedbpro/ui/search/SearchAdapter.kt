package com.attafakkur.moviedbpro.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.attafakkur.moviedbpro.R
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.databinding.ItemListBinding
import com.attafakkur.moviedbpro.ui.detail.DetailSearchActivity
import com.attafakkur.moviedbpro.utils.Constants.EXTRA_SEARCH
import com.attafakkur.moviedbpro.utils.Constants.IMAGE_URL
import com.bumptech.glide.Glide

class SearchAdapter :
    PagedListAdapter<SearchEntity, SearchAdapter.SearchHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchEntity>() {
            override fun areItemsTheSame(oldItem: SearchEntity, newItem: SearchEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SearchEntity, newItem: SearchEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    class SearchHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(search: SearchEntity) {
            with(binding) {
                title.text = search.title
                release.text = search.release_date
                ratingList.text = itemView.resources.getString(R.string.rating_d, search.vote_average)

                Glide.with(itemView.context)
                        .load(IMAGE_URL + search.poster_path)
                        .centerCrop()
                        .error(R.drawable.ic_action_err)
                        .into(poster)

                itemView.setOnClickListener {
                    Intent(itemView.context, DetailSearchActivity::class.java).apply {
                        putExtra(EXTRA_SEARCH, search)
                        itemView.context.startActivity(this)
                    }


                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}

