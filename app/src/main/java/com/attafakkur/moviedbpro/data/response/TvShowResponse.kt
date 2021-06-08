package com.attafakkur.moviedbpro.data.response

import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @field:SerializedName("results")
    val results: List<TvShowsEntity>
)