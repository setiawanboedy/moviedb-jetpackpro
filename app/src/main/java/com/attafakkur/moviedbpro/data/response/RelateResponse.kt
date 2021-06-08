package com.attafakkur.moviedbpro.data.response

import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import com.google.gson.annotations.SerializedName

data class RelateResponse(
    @field:SerializedName("results")
    val results: List<MoviesEntity>
)