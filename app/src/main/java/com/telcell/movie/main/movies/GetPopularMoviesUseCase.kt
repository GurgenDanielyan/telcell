package com.telcell.movie.main.movies

import com.telcell.movie.rest.RepoApi
import com.telcell.movie.useCaseCore.SingleUseCase
import io.reactivex.Single

class GetPopularMoviesUseCase(val repoApi: RepoApi) : SingleUseCase<Int, PopularVodResult>() {
    override fun buildUseCaseObservable(params: Int): Single<PopularVodResult> {
        return repoApi.getPopularMovies(params)
            .map {
                val items = mutableListOf<Vod>()
                it.results.forEach {
                    if (!it.adult) {
                        val posterUrl = "https://image.tmdb.org/t/p/w342${it.poster_path}"
                        items.add(Vod(it, posterUrl))
                    }
                }
                PopularVodResult(it.page, it.total_pages, it.total_results, items)
            }
    }

    override fun onErrorMap(throwable: Throwable): Throwable {
        return super.onErrorMap(throwable)
    }
}