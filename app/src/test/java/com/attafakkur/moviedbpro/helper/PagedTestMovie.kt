package com.attafakkur.moviedbpro.helper

import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.attafakkur.moviedbpro.data.local.entity.MoviesEntity
import java.util.concurrent.Executors

class PagedTestMovie(private val items: List<MoviesEntity>) :
    PositionalDataSource<MoviesEntity>() {
    companion object {
        fun snapshot(items: List<MoviesEntity> = listOf()): PagedList<MoviesEntity> {
            return PagedList.Builder(PagedTestMovie(items), 10)
                .setNotifyExecutor(Executors.newSingleThreadExecutor())
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
        }
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<MoviesEntity>
    ) {
        callback.onResult(items, 0, items.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MoviesEntity>) {
        val start = params.startPosition
        val end = params.startPosition + params.loadSize
        callback.onResult(items.subList(start, end))
    }
}