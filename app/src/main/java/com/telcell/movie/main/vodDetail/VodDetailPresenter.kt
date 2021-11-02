package com.telcell.movie.main.vodDetail

import com.google.gson.Gson
import com.telcell.movie.main.movies.Vod
import com.telcell.movie.rest.RepoApi
import moxy.MvpPresenter
import javax.inject.Inject

class VodDetailPresenter @Inject constructor(private val repoApi: RepoApi) : MvpPresenter<VodDetail>() {

    private lateinit var vod: Vod

    fun setup(vod: Vod) {
        this.vod = vod
    }

    override fun onFirstViewAttach() {
        viewState.showInfo(Gson().toJson(vod))
    }
}