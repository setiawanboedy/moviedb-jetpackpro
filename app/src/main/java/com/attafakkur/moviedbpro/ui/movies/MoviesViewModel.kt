package com.attafakkur.moviedbpro.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource

class MoviesViewModel(private val dbRepository: DBRepository): ViewModel() {

    fun getMoviesData(): LiveData<Resource<PagedList<MoviesEntity>>> =
        dbRepository.getMovies()
}