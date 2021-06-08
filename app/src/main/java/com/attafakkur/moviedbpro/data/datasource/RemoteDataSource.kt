package com.attafakkur.moviedbpro.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.data.network.RetrofitApi
import com.attafakkur.moviedbpro.data.response.MovieResponse
import com.attafakkur.moviedbpro.data.response.SearchResponse
import com.attafakkur.moviedbpro.data.response.TrendingResponse
import com.attafakkur.moviedbpro.data.response.TvShowResponse
import com.attafakkur.moviedbpro.data.response.apiresponse.ApiResponse
import com.attafakkur.moviedbpro.utils.EspressoIdlingResource.decrement
import com.attafakkur.moviedbpro.utils.EspressoIdlingResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
            RemoteDataSource().apply { instance = this }
        }
    }

    fun getMoviesPopular(): MutableLiveData<ApiResponse<MovieResponse>> {
        increment()
        val movieResponse = MutableLiveData<ApiResponse<MovieResponse>>()
        RetrofitApi.getRetrofitAPI().getMoviesList()
            .enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movieResponse.postValue(response.body()?.let { ApiResponse.success(it)})
                    decrement()
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.message
            }
        })
        return movieResponse
    }

    fun getTvPopular(): LiveData<ApiResponse<TvShowResponse>> {
        increment()
        val tvResponse = MutableLiveData<ApiResponse<TvShowResponse>>()
        RetrofitApi.getRetrofitAPI().getTvShowsList()
            .enqueue(object : Callback<TvShowResponse> {
                override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                    if (response.isSuccessful) {
                        tvResponse.postValue(response.body()?.let { ApiResponse.success(it)})
                        decrement()
                    }
                }
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    t.message
                }
            })
        return tvResponse
    }

    fun getDetailMovie(id: String): LiveData<ApiResponse<MoviesEntity>> {
        increment()
        val resultDetailMovie = MutableLiveData<ApiResponse<MoviesEntity>>()
        RetrofitApi.getRetrofitAPI().getMovieDetail(id)
            .enqueue(object : Callback<MoviesEntity> {
                override fun onResponse(
                    call: Call<MoviesEntity>,
                    response: Response<MoviesEntity>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultDetailMovie.value = ApiResponse.success(it) }
                        decrement()
                    }
                }

                override fun onFailure(call: Call<MoviesEntity>, t: Throwable) {
                    t.message
                }

            })
        return resultDetailMovie
    }

    fun getDetailTv(id: String): LiveData<ApiResponse<TvShowsEntity>> {
        increment()
        val resultDetailTv = MutableLiveData<ApiResponse<TvShowsEntity>>()
        RetrofitApi.getRetrofitAPI().getTvDetail(id)
            .enqueue(object : Callback<TvShowsEntity> {
                override fun onResponse(
                    call: Call<TvShowsEntity>,
                    response: Response<TvShowsEntity>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultDetailTv.value = ApiResponse.success(it) }
                        decrement()
                    }
                }

                override fun onFailure(call: Call<TvShowsEntity>, t: Throwable) {
                    t.message
                }

            })
        return resultDetailTv
    }

    fun getTrending(): LiveData<ApiResponse<TrendingResponse>> {
        increment()
        val trendResponse = MutableLiveData<ApiResponse<TrendingResponse>>()
        RetrofitApi.getRetrofitAPI().getTrendList()
            .enqueue(object : Callback<TrendingResponse> {
                override fun onResponse(
                    call: Call<TrendingResponse>,
                    response: Response<TrendingResponse>) {
                    if (response.isSuccessful) {
                        trendResponse.postValue(response.body()?.let { ApiResponse.success(it)})
                        decrement()
                    }
                }
                override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                    t.message
                }
            })
        return trendResponse
    }
    fun getDetailTrending(trend_id: String): LiveData<ApiResponse<TrendEntity>> {
        increment()
        val resultDetailTrend = MutableLiveData<ApiResponse<TrendEntity>>()
        RetrofitApi.getRetrofitAPI().getTrendDetail(trend_id)
            .enqueue(object : Callback<TrendEntity> {
                override fun onResponse(
                    call: Call<TrendEntity>,
                    response: Response<TrendEntity>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultDetailTrend.value = ApiResponse.success(it) }
                        decrement()
                    }
                }

                override fun onFailure(call: Call<TrendEntity>, t: Throwable) {
                    t.message
                }

            })
        return resultDetailTrend
    }

    fun getRelatedMovie(movie_id: String): LiveData<ApiResponse<MovieResponse>> {
        increment()
        val relateResponse = MutableLiveData<ApiResponse<MovieResponse>>()
        RetrofitApi.getRetrofitAPI().getRelateListMovie(movie_id)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    if (response.isSuccessful) {
                        relateResponse.postValue(response.body()?.let { ApiResponse.success(it)})
                        decrement()
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.message
                }
            })
        return relateResponse

    }

    fun getRelatedTv(tv_id: String): LiveData<ApiResponse<TvShowResponse>> {
        increment()
        val relateResponse = MutableLiveData<ApiResponse<TvShowResponse>>()
        RetrofitApi.getRetrofitAPI().getRelateListTv(tv_id)
            .enqueue(object : Callback<TvShowResponse> {
                override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                    if (response.isSuccessful) {
                        relateResponse.postValue(response.body()?.let { ApiResponse.success(it)})
                        decrement()
                    }
                }
                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    t.message
                }
            })
        return relateResponse
    }

    fun getSearch(query: String?): LiveData<ApiResponse<SearchResponse>> {
        increment()
        val searchResponse = MutableLiveData<ApiResponse<SearchResponse>>()
        if (query != null) {
            RetrofitApi.getRetrofitAPI().getSearchMovieDb(query)
                .enqueue(object : Callback<SearchResponse> {
                    override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                        if (response.isSuccessful) {
                            searchResponse.postValue(response.body()?.let { ApiResponse.success(it)})
                            decrement()
                        }
                    }
                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        t.message
                    }
                })
        }
        return searchResponse
    }

    fun getDetailSearch(id: String): LiveData<ApiResponse<SearchEntity>> {
        increment()
        val resultDetailSearch = MutableLiveData<ApiResponse<SearchEntity>>()
        RetrofitApi.getRetrofitAPI().getSearchDetail(id)
            .enqueue(object : Callback<SearchEntity> {
                override fun onResponse(
                    call: Call<SearchEntity>,
                    response: Response<SearchEntity>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { resultDetailSearch.value = ApiResponse.success(it) }
                        decrement()
                    }
                }

                override fun onFailure(call: Call<SearchEntity>, t: Throwable) {
                    t.message
                }

            })
        return resultDetailSearch
    }

}