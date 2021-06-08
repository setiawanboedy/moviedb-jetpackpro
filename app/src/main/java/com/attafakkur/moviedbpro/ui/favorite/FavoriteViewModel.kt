package com.attafakkur.moviedbpro.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository

class FavoriteViewModel(private val dbRepository: DBRepository) : ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MoviesEntity>> =
        dbRepository.getFavoriteMovie()


    fun getFavoriteTv(): LiveData<PagedList<TvShowsEntity>> {
        return dbRepository.getFavoriteTvShow()
    }

    fun getFavoriteTrend(): LiveData<PagedList<TrendEntity>> {
        return dbRepository.getFavoriteTrend()
    }
    fun getFavoriteSearch(): LiveData<PagedList<SearchEntity>> {
        return dbRepository.getFavoriteSearch()
    }
}