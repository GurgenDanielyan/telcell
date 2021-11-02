package com.telcell.movie.main

import com.telcell.movie.main.movies.Vod

interface MainConnector {
    fun onVodDetailAction(vod: Vod)
}