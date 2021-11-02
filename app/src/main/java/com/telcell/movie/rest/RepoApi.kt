package com.telcell.movie.rest

import com.telcell.movie.main.movies.PopularMovies
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoApi @Inject constructor(private val api: API) {

    fun getPopularMovies(page: Int): Single<PopularMovies> {
        val apiKey = "89115d0b6d3a0a790d749ea52eac1b21"
        val lang = "en-US"

        return api.getPopularMovies(apiKey, lang, page)
            .firstOrError()
    }
}