package com.attafakkur.moviedbpro.data.network

import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.response.MovieResponse
import com.attafakkur.moviedbpro.data.response.SearchResponse
import com.attafakkur.moviedbpro.data.response.TrendingResponse
import com.attafakkur.moviedbpro.data.response.TvShowResponse
import com.attafakkur.moviedbpro.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie?api_key=$API_KEY")
    fun getMoviesList() : Call<MovieResponse>

    @GET("discover/tv?api_key=$API_KEY")
    fun getTvShowsList() : Call<TvShowResponse>

    @GET("trending/movie/day?api_key=$API_KEY")
    fun getTrendList() : Call<TrendingResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getTrendDetail(
            @Path("movie_id") movie_id: String
    ) : Call<TrendEntity>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getMovieDetail(
            @Path("movie_id") movie_id: String
    ) : Call<MoviesEntity>

    @GET("tv/{tv_id}?api_key=$API_KEY")
    fun getTvDetail(
            @Path("tv_id") tv_id: String
    ) : Call<TvShowsEntity>

    @GET("movie/{movie_id}/similar?api_key=$API_KEY")
    fun getRelateListMovie(
        @Path("movie_id") movie_id: String
    ) : Call<MovieResponse>

    @GET("tv/{tv_id}/similar?api_key=$API_KEY")
    fun getRelateListTv(
            @Path("tv_id") tv_id: String
    ) : Call<TvShowResponse>

    @GET("search/movie?api_key=$API_KEY")
    fun getSearchMovieDb(
            @Query("query") key: String
    ) : Call <SearchResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    fun getSearchDetail(
        @Path("movie_id") movie_id: String
    ) : Call<SearchEntity>
}