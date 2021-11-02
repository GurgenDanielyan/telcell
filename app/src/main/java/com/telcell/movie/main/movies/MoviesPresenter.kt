package com.telcell.movie.main.movies

import com.telcell.movie.base.BasePresenter
import com.telcell.movie.useCaseCore.UseCaseFactory
import javax.inject.Inject

class MoviesPresenter @Inject constructor(val useCaseFactory: UseCaseFactory) : BasePresenter<MoviesView>() {
    private var loadedPage = 0
    private var totalItemsCount = 0
    private var isLoading = false
    private val loadedItems = mutableListOf<ListItem>()

    override fun onFirstViewAttach() {
        loadMoreData()
    }

    fun rvVisibleDataFinished() {
        loadMoreData()
    }

    fun loadMoreData() {
        if (isLoading || loadedPage != 0 && loadedItems.count() == totalItemsCount) {
            return
        }

        showProgressInList(true)

        isLoading = true
        val getPopularMoviesUseCase = useCaseFactory.createSingle<GetPopularMoviesUseCase>()
        rxExecutor.run(getPopularMoviesUseCase.execute(loadedPage + 1), handleProgress = loadedItems.isEmpty()) {
            loadedPage = it.page

            showProgressInList(false)

            val lastCount = loadedItems.count()
            val newItems = it.items
            val addedCount = newItems.count()
            loadedItems.addAll(newItems)
            viewState.setItemsWithAdd(loadedItems, lastCount, addedCount)
            isLoading = false
        }
    }

    private fun showProgressInList(state: Boolean) {
        if (loadedItems.isEmpty()) return

        if (state) {
            if (loadedItems.last() !is Progress) {
                loadedItems.add(Progress())
                viewState.setItemsWithAdd(loadedItems, loadedItems.count() - 1, 1)
            }
        } else {
            if (loadedItems.last() is Progress) {
                loadedItems.removeLast()
                viewState.setItemsWithRemove(loadedItems, loadedItems.count(), 1)
            }
        }
    }

    /**
     * Мог бы быть навигатор
     */
    fun onMovieClick(vod: Vod) {
        viewState.openVodDetail(vod)
    }

    /**
     * Error comes from use case and wrapped in ExceptionMapper
     */
    override fun handleError(throwable: Throwable, requestCode: Int) {
        when (throwable) {
//            is ...Exception -> {
//                doIt()
//            }
            else -> super.handleError(throwable, requestCode)
        }
    }
}