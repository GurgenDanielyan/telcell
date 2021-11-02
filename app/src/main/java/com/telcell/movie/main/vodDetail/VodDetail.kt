package com.telcell.movie.main.vodDetail

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface VodDetail : MvpView {
    @AddToEndSingle
    fun showInfo(text: String)
}