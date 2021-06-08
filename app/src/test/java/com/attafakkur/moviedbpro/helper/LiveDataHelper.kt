package com.attafakkur.moviedbpro.helper

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataHelper {
    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(movies: LiveData<T>): T? {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data[0] = t
                latch.countDown()
                movies.removeObserver(this)
            }
        }
        movies.observeForever(observer)
        try {
            latch.await(5, TimeUnit.SECONDS)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return data[0] as T
    }
}