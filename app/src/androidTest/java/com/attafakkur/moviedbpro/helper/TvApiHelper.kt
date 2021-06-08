package com.attafakkur.moviedbpro.helper

import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.attafakkur.moviedbpro.utils.Constants.API_KEY
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.io.IOException

object TvApiHelper {

        private val client = SyncHttpClient()
        private const val url = "https://api.themoviedb.org/3/discover/tv?api_key=${API_KEY}"


    val getApi : ArrayList<TvShowsEntity>
        get() {
            val tvList = arrayListOf<TvShowsEntity>()

            client.get(url, object : AsyncHttpResponseHandler(){
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray
                ) {
                    val result = String(responseBody)

                    try {

                        val responseObjects = JSONObject(result)
                        val jsonArray = responseObjects.getJSONArray("results")

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val id = jsonObject.getString("id")
                            val posterImg = jsonObject.getString("poster_path")
                            val title = jsonObject.getString("name")
                            val release = jsonObject.getString("first_air_date")
                            val overview = jsonObject.getString("overview")
                            val language = jsonObject.getString("original_language")
                            val backDrop = jsonObject.getString("backdrop_path")
                            val voteAverage = jsonObject.getString("vote_average")

                            val tv = TvShowsEntity(id, release, overview, language, posterImg, backDrop, title, voteAverage)

                            tvList.add(tv)
                        }
                    }catch (e: IOException){
                        e.message
                    }
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
                ) {
                    error?.message
                }

            })
            return tvList
        }

}