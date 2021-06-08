package com.attafakkur.moviedbpro.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource

class TrendViewModel(private val dbRepository: DBRepository) : ViewModel() {
    fun getTrendData(): LiveData<Resource<PagedList<TrendEntity>>> =
            dbRepository.getTrending()
}