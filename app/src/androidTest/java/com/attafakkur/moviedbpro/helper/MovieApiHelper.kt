package com.attafakkur.moviedbpro.helper

import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.SyncHttpClient
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.io.IOException

object MovieApiHelper {

        private val client = SyncHttpClient()
        private const val apiKey = "a5b7b6baa4f0fe2efccccd5e3b6dc2d4"
        private const val url = "https://api.themoviedb.org/3/discover/movie?api_key=$apiKey"


    val getApi : ArrayList<MoviesEntity>
        get() {
            val movieList = arrayListOf<MoviesEntity>()

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
                            val title = jsonObject.getString("title")
                            val release = jsonObject.getString("release_date")
                            val overview = jsonObject.getString("overview")
                            val language = jsonObject.getString("original_language")
                            val backDrop = jsonObject.getString("backdrop_path")
                            val voteAverage = jsonObject.getString("vote_average")

                            val movies = MoviesEntity(id, posterImg, title, release, overview, language, backDrop, voteAverage)

                            movieList.add(movies)
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
            return movieList
        }

}