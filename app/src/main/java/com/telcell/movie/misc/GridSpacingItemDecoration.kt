package com.telcell.movie.misc

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(val spanCount: Int, val spacingHorizontal: Int, val spacingVertical: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = column * spacingHorizontal / spanCount
        outRect.right = spacingHorizontal - (column + 1) * spacingHorizontal / spanCount
        if (position >= spanCount) {
            outRect.top = spacingVertical
        }
    }
}
