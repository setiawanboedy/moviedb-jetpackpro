package com.attafakkur.moviedbpro.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favorite_search")
@Parcelize
data class SearchEntity(
        @PrimaryKey
        @NotNull
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "poster_path")
        val poster_path: String?,
        @ColumnInfo(name = "title")
        val title: String?,
        @ColumnInfo(name = "release_date")
        val release_date: String?,
        @ColumnInfo(name = "overview")
        val overview: String?,
        @ColumnInfo(name = "original_language")
        val original_language: String?,
        @ColumnInfo(name = "backdrop_path")
        val backdrop_path: String?,
        @ColumnInfo(name = "vote_average")
        val vote_average: String?,
        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
): Parcelable
