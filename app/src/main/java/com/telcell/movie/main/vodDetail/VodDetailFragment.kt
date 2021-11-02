package com.telcell.movie.main.vodDetail

import android.os.Bundle
import com.telcell.movie.R
import com.telcell.movie.base.BaseFragment
import com.telcell.movie.di.ComponentManager
import com.telcell.movie.main.MainConnector
import com.telcell.movie.main.movies.Vod
import kotlinx.android.synthetic.main.fragment_vod_detail.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class VodDetailFragment : BaseFragment<MainConnector>(), VodDetail {

    companion object {
        const val BUNDLE_KEY = "bundleKey"

        fun newInstance(vod: Vod) = VodDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDLE_KEY, vod)
            }
        }
    }

    @Inject
    lateinit var presenterProvider: Provider<VodDetailPresenter>

    private val presenter by moxyPresenter {
        ComponentManager.getInstance().getMainComponent().inject(this)
        presenterProvider.get()
    }

    override val getLayout: Int
        get() = R.layout.fragment_vod_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<Vod>(BUNDLE_KEY)?.apply {
            presenter.setup(this)
        } ?: run {
            throw Exception("Where is arguments ?")
        }
    }

    override fun showInfo(text: String) {
        dummyTV.setText(text)
    }
}