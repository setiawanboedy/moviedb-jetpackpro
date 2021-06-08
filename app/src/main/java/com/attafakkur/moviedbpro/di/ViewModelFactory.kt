package com.attafakkur.moviedbpro.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.di.Injection.injectRepository
import com.attafakkur.moviedbpro.ui.detail.DetailViewModel
import com.attafakkur.moviedbpro.ui.favorite.FavoriteViewModel
import com.attafakkur.moviedbpro.ui.movies.MoviesViewModel
import com.attafakkur.moviedbpro.ui.search.SearchViewModel
import com.attafakkur.moviedbpro.ui.trending.TrendViewModel
import com.attafakkur.moviedbpro.ui.tvshows.TvShowsViewModel

class ViewModelFactory private constructor(private val dbRepository: DBRepository)
    : ViewModelProvider.NewInstanceFactory(){

        companion object {
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    ViewModelFactory(injectRepository(context)).apply {
                        instance = this
                    }
                }
        }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) ->
                MoviesViewModel(dbRepository) as T
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) ->
                TvShowsViewModel(dbRepository) as T
            modelClass.isAssignableFrom(TrendViewModel::class.java) ->
                TrendViewModel(dbRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(dbRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) ->
                SearchViewModel(dbRepository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->
                FavoriteViewModel(dbRepository) as T
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}