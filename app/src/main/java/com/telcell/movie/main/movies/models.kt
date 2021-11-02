package com.telcell.movie.main.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Models from backend

data class PopularMovies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

@Parcelize
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
) : Parcelable

// In app models

enum class Type {
    VOD, PROGRESS
}

interface ListItem {
    fun getType(): Int
}

data class PopularVodResult(
    val page: Int,
    val totalPagesCount: Int,
    val totalItemsCount: Int,
    val items: List<Vod>
)

@Parcelize
data class Vod(
    val backendObj: Movie,
    val posterUrl: String
) : Parcelable, ListItem {
    override fun getType(): Int {
        return Type.VOD.ordinal
    }
}

class Progress() : ListItem {
    override fun getType(): Int {
        return Type.PROGRESS.ordinal
    }
}