package com.attafakkur.moviedbpro.data.response

import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import com.google.gson.annotations.SerializedName

data class SearchResponse(
        @field:SerializedName("results")
        val results: List<SearchEntity>
)