package com.attafakkur.moviedbpro.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.vo.Resource

class DetailViewModel(
        private val dbRepository: DBRepository
        ) : ViewModel() {

    fun getDetailMovies(movieId: String): LiveData<Resource<MoviesEntity>> =
        dbRepository.getDetailMovie(movieId)

    fun getDetailTv(tvId: String): LiveData<Resource<TvShowsEntity>> =
        dbRepository.getDetailTv(tvId)

    fun getDetailTrend(movieId: String): LiveData<Resource<TrendEntity>> =
        dbRepository.getDetailTrending(movieId)

    fun getRelateDataMovie(movieId: String): LiveData<Resource<PagedList<MoviesEntity>>> =
        dbRepository.getRelateMovie(movieId)

    fun getRelateDataTv(tvId: String): LiveData<Resource<PagedList<TvShowsEntity>>> =
        dbRepository.getRelateTv(tvId)

    fun getDetailSearch(movieId: String): LiveData<Resource<SearchEntity>> =
        dbRepository.getDetailSearch(movieId)

    // Favorite
    fun setFavoriteMovie(moviesEntity: MoviesEntity) {
            dbRepository.setFavoriteMovie(moviesEntity)
    }

    fun setFavoriteTrend(trendEntity: TrendEntity) {
        dbRepository.setFavoriteTrend(trendEntity)
    }

    fun setFavoriteTv(tvShowsEntity: TvShowsEntity) {
        dbRepository.setFavoriteTvShow(tvShowsEntity)
    }

    fun setFavoriteSearch(searchEntity: SearchEntity) {
        dbRepository.setFavoriteSearch(searchEntity)
    }

}