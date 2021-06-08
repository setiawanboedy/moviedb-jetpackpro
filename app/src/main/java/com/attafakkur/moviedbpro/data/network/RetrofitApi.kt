package com.attafakkur.moviedbpro.data.network

import com.attafakkur.moviedbpro.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    companion object ObjFactory {
        fun getRetrofitAPI(): ApiService {

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)

        }
    }
}