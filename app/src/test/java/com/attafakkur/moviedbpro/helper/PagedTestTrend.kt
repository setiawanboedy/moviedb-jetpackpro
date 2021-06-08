package com.attafakkur.moviedbpro.helper

import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.attafakkur.moviedbpro.data.local.entity.TrendEntity
import java.util.concurrent.Executors

class PagedTestTrend(private val items: List<TrendEntity>) :
    PositionalDataSource<TrendEntity>() {
    companion object {
        fun snapshot(items: List<TrendEntity> = listOf()): PagedList<TrendEntity> {
            return PagedList.Builder(PagedTestTrend(items), 10)
                .setNotifyExecutor(Executors.newSingleThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<TrendEntity>
    ) {
        callback.onResult(items, 0, items.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TrendEntity>) {
        val start = params.startPosition
        val end = params.startPosition + params.loadSize
        callback.onResult(items.subList(start, end))
    }
}