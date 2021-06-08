package com.attafakkur.moviedbpro.helper

import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.attafakkur.moviedbpro.data.local.entity.TvShowsEntity
import java.util.concurrent.Executors

class PagedTestTv(private val items: List<TvShowsEntity>) :
    PositionalDataSource<TvShowsEntity>() {
    companion object {
        fun snapshot(items: List<TvShowsEntity> = listOf()): PagedList<TvShowsEntity> {
            return PagedList.Builder(PagedTestTv(items), 10)
                .setNotifyExecutor(Executors.newSingleThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<TvShowsEntity>
    ) {
        callback.onResult(items, 0, items.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShowsEntity>) {
        val start = params.startPosition
        val end = params.startPosition + params.loadSize
        callback.onResult(items.subList(start, end))
    }
}