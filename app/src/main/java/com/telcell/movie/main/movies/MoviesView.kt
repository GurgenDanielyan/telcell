package com.telcell.movie.main.movies

import com.telcell.movie.base.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface MoviesView : BaseView {

    @AddToEndSingle
    fun setItemsWithAdd(itemsList: List<ListItem>, updatePosition: Int, count: Int)

    @OneExecution
    fun setItemsWithRemove(itemsList: List<ListItem>, updatePosition: Int, count: Int)

    @OneExecution
    fun openVodDetail(vod: Vod)
}