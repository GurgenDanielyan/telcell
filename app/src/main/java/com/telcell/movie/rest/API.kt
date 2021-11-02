package com.telcell.movie.rest

import com.telcell.movie.main.movies.PopularMovies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    companion object {
        const val API_KEY = "api_key"
        const val LANGUAGE = "language"
        const val PAGE = "page"

        const val PREFIX = "/3"
        const val PREFIX_MOVIE = "${PREFIX}/movie"

        const val GET_POPULAR_MOVIES = "${PREFIX_MOVIE}/popular"
    }

    @GET(GET_POPULAR_MOVIES)
    fun getPopularMovies(@Query(API_KEY) apiKey: String, @Query(LANGUAGE) lang: String, @Query(PAGE) page: Int): Observable<PopularMovies>
}