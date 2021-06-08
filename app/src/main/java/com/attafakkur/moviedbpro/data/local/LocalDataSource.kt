package com.attafakkur.moviedbpro.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.attafakkur.moviedbpro.data.local.db.MovieDao
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao).apply {
                INSTANCE = this
            }
    }

    fun getMovies(): DataSource.Factory<Int, MoviesEntity> = movieDao.getMovies()

    fun getTvShows(): DataSource.Factory<Int, TvShowsEntity> = movieDao.getTvShows()

    fun getMovieById(id: String): LiveData<MoviesEntity> = movieDao.getMovieById(id)

    fun getTvShowById(id: String): LiveData<TvShowsEntity> = movieDao.getTvShowById(id)

    fun insertMovies(movies: List<MoviesEntity>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowsEntity>) = movieDao.insertTvShows(tvShows)

    fun getTrend(): DataSource.Factory<Int, TrendEntity> = movieDao.getTrend()

    fun getSearch(): DataSource.Factory<Int, SearchEntity> = movieDao.getMovieSearch()

    fun getMoviesRelate(): DataSource.Factory<Int, MoviesEntity> = movieDao.getMovieRelate()

    fun getTvRelate(): DataSource.Factory<Int, TvShowsEntity> = movieDao.getTvRelate()

    fun getTrendById(id: String): LiveData<TrendEntity> = movieDao.getTrendById(id)

    fun getSearchById(id: String): LiveData<SearchEntity> = movieDao.getSearchById(id)

    fun insertTrend(movies: List<TrendEntity>) = movieDao.insertTrend(movies)

    fun insertSearch(search: List<SearchEntity>) = movieDao.insertSearch(search)

    fun insertRelateMovies(movies: List<MoviesEntity>) = movieDao.insertMovieRelate(movies)

    fun insertTvRelate(tvShows: List<TvShowsEntity>) = movieDao.insertTvRelate(tvShows)

    fun updateMovie(movie: MoviesEntity) = movieDao.updateMovie(movie)

    fun updateTvShow(tvShow: TvShowsEntity) = movieDao.updateTvShow(tvShow)

    fun updateTrend(trend: TrendEntity) = movieDao.updateTrend(trend)

    fun updateSearch(search: SearchEntity) = movieDao.updateSearch(search)

    fun getFavoriteMovie(): DataSource.Factory<Int, MoviesEntity> = movieDao.getFavoriteMovie()

    fun getFavoriteSearch(): DataSource.Factory<Int, SearchEntity> = movieDao.getFavoriteSearch()

    fun getFavoriteTrend(): DataSource.Factory<Int, TrendEntity> = movieDao.getFavoriteTrend()

    fun setMovieStatus(movie: MoviesEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateMovie(movie)
    }

    fun setSearchStatus(movie: SearchEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateSearch(movie)
    }

    fun setTrendStatus(movie: TrendEntity) {
        movie.isFavorite = !movie.isFavorite
        movieDao.updateTrend(movie)
    }

    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowsEntity> = movieDao.getFavoriteTvShow()

    fun setTvShowStatus(tvShow: TvShowsEntity) {
        tvShow.isFavorite = !tvShow.isFavorite
        movieDao.updateTvShow(tvShow)
    }
}