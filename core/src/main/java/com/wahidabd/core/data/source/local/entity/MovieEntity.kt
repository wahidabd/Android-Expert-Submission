package com.wahidabd.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "poster_path")
    val poster_path: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String? = null,

    @ColumnInfo(name = "release_date")
    val release_date: String? = null,

    @ColumnInfo(name = "vote_average")
    val vote_average: Double? = null,

    @ColumnInfo(name = "vote_count")
    val vote_count: Int? = null,

    @ColumnInfo(name = "original_language")
    val original_language: String? = null,

    @ColumnInfo(name = "is_favorite")
    var is_favorite: Boolean = false
)