package com.attafakkur.moviedbpro.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.attafakkur.moviedbpro.data.datasource.DBDataSource
import com.attafakkur.moviedbpro.data.datasource.RemoteDataSource
import com.attafakkur.moviedbpro.data.local.LocalDataSource
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.response.MovieResponse
import com.attafakkur.moviedbpro.data.response.SearchResponse
import com.attafakkur.moviedbpro.data.response.TrendingResponse
import com.attafakkur.moviedbpro.data.response.TvShowResponse
import com.attafakkur.moviedbpro.data.response.apiresponse.ApiResponse
import com.attafakkur.moviedbpro.data.response.apiresponse.NetworkBoundResource
import com.attafakkur.moviedbpro.utils.AppExecutors
import com.attafakkur.moviedbpro.vo.Resource

class FakeDBRepositoryTest (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DBDataSource {

    override fun getMovies(): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, MovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                return LivePagedListBuilder(localDataSource.getMovies(), DBRepository.config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getMoviesPopular()
            }

            override fun saveCallResult(data: MovieResponse) {
                return localDataSource.insertMovies(data.results)

            }

        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, TvShowResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                return LivePagedListBuilder(localDataSource.getTvShows(), DBRepository.config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> {
                return remoteDataSource.getTvPopular()
            }

            override fun saveCallResult(data: TvShowResponse) {
                return localDataSource.insertTvShows(data.results)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(movieId: String): LiveData<Resource<MoviesEntity>> {
        return object : NetworkBoundResource<MoviesEntity, MoviesEntity>(appExecutors){
            override fun loadFromDB(): LiveData<MoviesEntity> {
                return localDataSource.getMovieById(movieId)
            }

            override fun shouldFetch(data: MoviesEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<MoviesEntity>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override fun saveCallResult(data: MoviesEntity) {
                val movies = with(data){
                    MoviesEntity(id, poster_path, title, release_date, overview, original_language, backdrop_path, vote_average)
                }
                localDataSource.updateMovie(movies)
            }

        }.asLiveData()
    }

    override fun getDetailTv(tvId: String): LiveData<Resource<TvShowsEntity>> {
        return object : NetworkBoundResource<TvShowsEntity, TvShowsEntity>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowsEntity> {
                return localDataSource.getTvShowById(tvId)
            }

            override fun shouldFetch(data: TvShowsEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<TvShowsEntity>> {
                return remoteDataSource.getDetailTv(tvId)
            }

            override fun saveCallResult(data: TvShowsEntity) {
                val tv = with(data){
                    TvShowsEntity(id, first_air_date, overview, original_language, poster_path, backdrop_path, name, vote_average)
                }
                localDataSource.updateTvShow(tv)
            }

        }.asLiveData()
    }

    override fun getTrending(): LiveData<Resource<PagedList<TrendEntity>>> {
        return object : NetworkBoundResource<PagedList<TrendEntity>, TrendingResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TrendEntity>> {
                return LivePagedListBuilder(localDataSource.getTrend(), DBRepository.config).build()
            }

            override fun shouldFetch(data: PagedList<TrendEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TrendingResponse>> {
                return remoteDataSource.getTrending()
            }

            override fun saveCallResult(data: TrendingResponse) {
                return localDataSource.insertTrend(data.results)
            }

        }.asLiveData()
    }

    override fun getDetailTrending(trendId: String): LiveData<Resource<TrendEntity>> {
        return object : NetworkBoundResource<TrendEntity, TrendEntity>(appExecutors){
            override fun loadFromDB(): LiveData<TrendEntity> {
                return localDataSource.getTrendById(trendId)
            }

            override fun shouldFetch(data: TrendEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<TrendEntity>> {
                return remoteDataSource.getDetailTrending(trendId)
            }

            override fun saveCallResult(data: TrendEntity) {
                val tv = with(data){
                    TrendEntity(id, overview, original_language, title, poster_path, backdrop_path, release_date, vote_average)
                }
                localDataSource.updateTrend(tv)
            }

        }.asLiveData()
    }

    override fun getRelateMovie(movieId: String): LiveData<Resource<PagedList<MoviesEntity>>> {
        return object : NetworkBoundResource<PagedList<MoviesEntity>, MovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MoviesEntity>> {
                return LivePagedListBuilder(localDataSource.getMoviesRelate(), DBRepository.config).build()
            }

            override fun shouldFetch(data: PagedList<MoviesEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getRelatedMovie(movieId)
            }

            override fun saveCallResult(data: MovieResponse) {
                return localDataSource.insertRelateMovies(data.results)
            }

        }.asLiveData()

    }

    override fun getRelateTv(tvId: String): LiveData<Resource<PagedList<TvShowsEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowsEntity>, TvShowResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowsEntity>> {
                return LivePagedListBuilder(localDataSource.getTvRelate(), DBRepository.config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowsEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> {
                return remoteDataSource.getRelatedTv(tvId)
            }

            override fun saveCallResult(data: TvShowResponse) {
                return localDataSource.insertTvRelate(data.results)
            }

        }.asLiveData()
    }

    override fun getSearch(query: String?): LiveData<Resource<PagedList<SearchEntity>>> {
        return object : NetworkBoundResource<PagedList<SearchEntity>, SearchResponse>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<SearchEntity>> {
                return LivePagedListBuilder(localDataSource.getSearch(), DBRepository.config).build()
            }

            override fun shouldFetch(data: PagedList<SearchEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<SearchResponse>> {
                return remoteDataSource.getSearch(query)
            }

            override fun saveCallResult(data: SearchResponse) {
                return data.results.let { localDataSource.insertSearch(it) }
            }


        }.asLiveData()
    }

    override fun getDetailSearch(movieId: String): LiveData<Resource<SearchEntity>> {
        return object : NetworkBoundResource<SearchEntity, SearchEntity>(appExecutors){
            override fun loadFromDB(): LiveData<SearchEntity> {
                return localDataSource.getSearchById(movieId)
            }

            override fun shouldFetch(data: SearchEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<SearchEntity>> {
                return remoteDataSource.getDetailSearch(movieId)
            }

            override fun saveCallResult(data: SearchEntity) {
                val search = with(data){
                    SearchEntity(id, poster_path, title, release_date, overview, original_language, backdrop_path, vote_average)
                }
                localDataSource.updateSearch(search)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MoviesEntity>> {
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), DBRepository.config).build()
    }

    override fun setFavoriteMovie(movie: MoviesEntity) {
        localDataSource.setMovieStatus(movie)
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowsEntity>> {
        return LivePagedListBuilder(localDataSource.getFavoriteTvShow(), DBRepository.config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowsEntity) {
        localDataSource.setTvShowStatus(tvShow)
    }

    override fun getFavoriteTrend(): LiveData<PagedList<TrendEntity>> {
        return LivePagedListBuilder(localDataSource.getFavoriteTrend(), DBRepository.config).build()
    }

    override fun setFavoriteTrend(trend: TrendEntity) {
        localDataSource.setTrendStatus(trend)
    }

    override fun getFavoriteSearch(): LiveData<PagedList<SearchEntity>> {
        return LivePagedListBuilder(localDataSource.getFavoriteSearch(), DBRepository.config).build()
    }

    override fun setFavoriteSearch(search: SearchEntity) {
        localDataSource.setSearchStatus(search)
    }
}