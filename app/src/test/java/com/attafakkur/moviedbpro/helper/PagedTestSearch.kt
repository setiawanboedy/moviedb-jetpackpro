package com.attafakkur.moviedbpro.helper

import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.attafakkur.moviedbpro.data.local.entity.SearchEntity
import java.util.concurrent.Executors

class PagedTestSearch(private val items: List<SearchEntity>) :
    PositionalDataSource<SearchEntity>() {
    companion object {
        fun snapshot(items: List<SearchEntity> = listOf()): PagedList<SearchEntity> {
            return PagedList.Builder(PagedTestSearch(items), 10)
                .setNotifyExecutor(Executors.newSingleThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<SearchEntity>
    ) {
        callback.onResult(items, 0, items.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<SearchEntity>) {
        val start = params.startPosition
        val end = params.startPosition + params.loadSize
        callback.onResult(items.subList(start, end))
    }
}