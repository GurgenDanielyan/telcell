package com.telcell.movie.main.movies

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

private const val VOD_POSTER_RATIO = 120 / 170f


class CalculateHeighFromWidthVodPosterIV : AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val availableWidth = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(availableWidth, (availableWidth / VOD_POSTER_RATIO).toInt())
    }
}