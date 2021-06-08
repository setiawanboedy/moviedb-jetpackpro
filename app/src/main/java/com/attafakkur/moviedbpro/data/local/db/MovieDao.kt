package com.attafakkur.moviedbpro.data.local.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorite_movie")
    fun getMovies(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM favorite_tv")
    fun getTvShows(): DataSource.Factory<Int, TvShowsEntity>

    @Transaction
    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    fun getMovieById(id: String): LiveData<MoviesEntity>

    @Transaction
    @Query("SELECT * FROM favorite_tv WHERE id = :id")
    fun getTvShowById(id: String): LiveData<TvShowsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MoviesEntity::class)
    fun insertMovies(movies: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowsEntity::class)
    fun insertTvShows(tvShows: List<TvShowsEntity>)

    @Query("SELECT * FROM favorite_trend")
    fun getTrend(): DataSource.Factory<Int, TrendEntity>

    @Query("SELECT * FROM favorite_movie")
    fun getMovieRelate(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM favorite_tv")
    fun getTvRelate(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM favorite_search")
    fun getMovieSearch(): DataSource.Factory<Int, SearchEntity>

    @Transaction
    @Query("SELECT * FROM favorite_search WHERE id = :id")
    fun getSearchById(id: String): LiveData<SearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(search: List<SearchEntity>)

    @Transaction
    @Query("SELECT * FROM favorite_trend WHERE id = :id")
    fun getTrendById(id: String): LiveData<TrendEntity>

    @Transaction
    @Query("SELECT * FROM favorite_tv WHERE id = :id")
    fun getTvRelateById(id: Int): LiveData<TvShowsEntity>

    @Transaction
    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    fun getMovieRelateById(id: Int): LiveData<MoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TrendEntity::class)
    fun insertTrend(movies: List<TrendEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MoviesEntity::class)
    fun insertMovieRelate(tvShows: List<MoviesEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowsEntity::class)
    fun insertTvRelate(tvShows: List<TvShowsEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Update
    fun updateTvShow(tvShow: TvShowsEntity)

    @Update
    fun updateTrend(trend: TrendEntity)

    @Update
    fun updateSearch(search: SearchEntity)

    @Query("SELECT * FROM favorite_movie WHERE isFavorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM favorite_tv WHERE isFavorite = 1")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShowsEntity>

    @Query("SELECT * FROM favorite_search WHERE isFavorite = 1")
    fun getFavoriteSearch(): DataSource.Factory<Int, SearchEntity>

    @Query("SELECT * FROM favorite_trend WHERE isFavorite = 1")
    fun getFavoriteTrend(): DataSource.Factory<Int, TrendEntity>
}