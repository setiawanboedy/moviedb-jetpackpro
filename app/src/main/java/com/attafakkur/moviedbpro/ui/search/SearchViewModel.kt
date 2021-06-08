package com.attafakkur.moviedbpro.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource

class SearchViewModel(private val dbRepository: DBRepository): ViewModel() {

    fun getSearchData(query: String?): LiveData<Resource<PagedList<SearchEntity>>> =
        dbRepository.getSearch(query)
}