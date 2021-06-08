package com.attafakkur.moviedbpro.di

import android.content.Context
import com.attafakkur.moviedbpro.data.datasource.RemoteDataSource
import com.attafakkur.moviedbpro.data.local.LocalDataSource
import com.attafakkur.moviedbpro.data.local.db.MovieDatabase
import com.attafakkur.moviedbpro.data.repository.DBRepository
import com.attafakkur.moviedbpro.utils.AppExecutors

object Injection {

    fun injectRepository(context: Context): DBRepository {

        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val appExecutors = AppExecutors()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        return DBRepository.getInstance(remoteDataSource, appExecutors, localDataSource)
    }

    fun injectLocal(context: Context): LocalDataSource {
        val database = MovieDatabase.getInstance(context)

        return LocalDataSource.getInstance(database.movieDao())
    }
}