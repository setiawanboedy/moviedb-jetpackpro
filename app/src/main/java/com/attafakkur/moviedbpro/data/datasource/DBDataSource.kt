package com.attafakkur.moviedbpro.data.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.vo.Resource

interface DBDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>>
    fun getDetailMovie(movieId: String): LiveData<Resource<MoviesEntity>>
    fun getDetailTv(tvId: String): LiveData<Resource<TvShowsEntity>>
    fun getTrending(): LiveData<Resource<PagedList<TrendEntity>>>
    fun getDetailTrending(trendId: String): LiveData<Resource<TrendEntity>>
    fun getRelateMovie(movieId: String): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getRelateTv(tvId: String): LiveData<Resource<PagedList<TvShowsEntity>>>
    fun getSearch(query: String?): LiveData<Resource<PagedList<SearchEntity>>>
    fun getDetailSearch(movieId: String): LiveData<Resource<SearchEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MoviesEntity>>
    fun setFavoriteMovie(movie: MoviesEntity)
    fun getFavoriteTvShow(): LiveData<PagedList<TvShowsEntity>>
    fun setFavoriteTvShow(tvShow: TvShowsEntity)

    fun getFavoriteTrend(): LiveData<PagedList<TrendEntity>>
    fun setFavoriteTrend(trend: TrendEntity)
    fun getFavoriteSearch(): LiveData<PagedList<SearchEntity>>
    fun setFavoriteSearch(search: SearchEntity)
}