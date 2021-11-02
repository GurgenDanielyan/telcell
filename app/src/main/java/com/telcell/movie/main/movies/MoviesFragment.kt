package com.telcell.movie.main.movies

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.telcell.movie.R
import com.telcell.movie.base.BaseFragment
import com.telcell.movie.di.ComponentManager
import com.telcell.movie.main.MainConnector
import com.telcell.movie.misc.EndlessRecyclerViewScrollListener
import com.telcell.movie.misc.ProgressViewStub
import kotlinx.android.synthetic.main.fragment_movies.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MoviesFragment : BaseFragment<MainConnector>(), MoviesView, MovieClickListener {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    lateinit var adapter: VodListAdapter

    @Inject
    lateinit var presenterProvider: Provider<MoviesPresenter>

    private val presenter by moxyPresenter {
        ComponentManager.getInstance().getMainComponent().inject(this)
        presenterProvider.get()
    }

    override val getLayout: Int
        get() = R.layout.fragment_movies

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressViewStub = ProgressViewStub.create(moviesContainer)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        val gridLayoutManager = vodListRV.layoutManager as GridLayoutManager
        val spanCount = gridLayoutManager.spanCount

        adapter = VodListAdapter(listOf(), this, spanCount).apply {
            vodListRV.adapter = this
        }

        vodListRV.addOnScrollListener(object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                presenter.rvVisibleDataFinished()
            }
        })

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter.isItemProgress(position)) spanCount else 1
            }
        }
    }

    override fun setItemsWithAdd(itemsList: List<ListItem>, updatePosition: Int, count: Int) {
        adapter.setItemsWithAdd(itemsList, updatePosition, count)
    }

    override fun setItemsWithRemove(itemsList: List<ListItem>, updatePosition: Int, count: Int) {
        adapter.setItemsWithRemove(itemsList, updatePosition, count)
    }

    override fun onMovieClick(vod: Vod) {
        presenter.onMovieClick(vod)
    }

    override fun openVodDetail(vod: Vod) {
        getConnector()?.onVodDetailAction(vod)
    }
}