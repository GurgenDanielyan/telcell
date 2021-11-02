@file:Suppress("UNCHECKED_CAST")

package com.telcell.movie.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.telcell.movie.R
import com.telcell.movie.misc.ProgressViewStub
import moxy.MvpAppCompatFragment

/**
 * T -> type of activity connector
 */

abstract class BaseFragment<T> : MvpAppCompatFragment(), BaseView {
    protected var progressViewStub: ProgressViewStub? = null

    abstract val getLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout, container, false)
    }

    protected fun getConnector(): T? {
        return context as? T
    }

    override fun showNetworkError(requestCode: Int) {
        view?.apply {
            Snackbar.make(this, R.string.no_network, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showFatalServerError(requestCode: Int) {
        view?.apply {
            Snackbar.make(this, R.string.something_wrong, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showServerError(requestCode: Int) {
        view?.apply {
            Snackbar.make(this, R.string.something_wrong, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun showProgress(state: Boolean) {
        if (state) {
            progressViewStub?.showProgress()
        } else {
            progressViewStub?.hideProgress()
        }
    }
}