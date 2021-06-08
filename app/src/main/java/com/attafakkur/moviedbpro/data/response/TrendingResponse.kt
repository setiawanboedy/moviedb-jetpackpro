package com.attafakkur.moviedbpro.data.response

import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @field:SerializedName("results")
    val results: List<TrendEntity>
)