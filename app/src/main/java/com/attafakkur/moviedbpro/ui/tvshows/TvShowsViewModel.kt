package com.attafakkur.moviedbpro.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource

class TvShowsViewModel(private val dbRepository: DBRepository) : ViewModel() {
    fun getTvData(): LiveData<Resource<PagedList<TvShowsEntity>>> =
            dbRepository.getTvShows()
}