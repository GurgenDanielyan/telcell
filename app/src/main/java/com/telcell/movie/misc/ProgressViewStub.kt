package com.telcell.movie.misc

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
import com.telcell.movie.R

@SuppressLint("ClickableViewAccessibility")
class ProgressViewStub : View.OnAttachStateChangeListener {
    companion object {
        fun create(frameLayout: FrameLayout): ProgressViewStub {
            return ProgressViewStub(frameLayout, -1)
        }

        fun create(frameLayout: FrameLayout, index: Int): ProgressViewStub {
            return ProgressViewStub(frameLayout, index)
        }

        fun create(relativeLayout: RelativeLayout): ProgressViewStub {
            return ProgressViewStub(relativeLayout, -1)
        }

        fun create(relativeLayout: RelativeLayout, index: Int): ProgressViewStub {
            return ProgressViewStub(relativeLayout, index)
        }
    }

    @LayoutRes
    private val inflatedId = R.layout.progress_layout

    private var mView: View
    private var mInitializeComplete = false
    var isVisibilityState = false
        private set

    private constructor(frameLayout: FrameLayout, index: Int) {
        mView = onCreateView(frameLayout.context)
        frameLayout.addView(mView, index)
    }

    private constructor(relativeLayout: RelativeLayout, index: Int) {
        mView = onCreateView(relativeLayout.context)
        val params = RelativeLayout.LayoutParams(-1, -1)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        relativeLayout.addView(mView, index, params)
    }

    private fun onCreateView(context: Context): View {
        return LayoutInflater.from(context).inflate(inflatedId, null)
    }

    fun initialize(): ProgressViewStub {
        mView.setOnTouchListener { _: View?, _: MotionEvent? -> true }
        mInitializeComplete = true
        mView.addOnAttachStateChangeListener(this)
        return this
    }

    override fun onViewDetachedFromWindow(v: View) {
        hideProgress()
    }

    fun showProgress(): ProgressViewStub {
        if (isVisibilityState) return this

        if (!mInitializeComplete) {
            initialize()
        }
        adaptToVisibility(View.VISIBLE)
        return this
    }

    private fun adaptToVisibility(visibility: Int) {
        if (visibility == View.VISIBLE) {
            isVisibilityState = true
            mView.visibility = visibility
        } else {
            isVisibilityState = false
            mView.visibility = visibility
        }
    }

    fun hideProgress() {
        if (!isVisibilityState) return

        adaptToVisibility(View.GONE)
    }

    override fun onViewAttachedToWindow(v: View) {
        // Do nothing
    }
}