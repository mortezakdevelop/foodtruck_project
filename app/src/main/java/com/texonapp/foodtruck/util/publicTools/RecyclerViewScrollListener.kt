package com.texonapp.foodtruck.util.publicTools

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewScrollListener(
    private val linearLayoutManager: LinearLayoutManager,
    private val onLoadMoreListener: OnLoadMoreListener?
) :
    RecyclerView.OnScrollListener() {
    private var loading = false
    private var END_OF_FEED_ADDED = false
    private val pauseListening = false
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = linearLayoutManager.itemCount
        val visibleItemCount = linearLayoutManager.childCount
        val pastVisiblesItem = linearLayoutManager.findFirstVisibleItemPosition()
        if (!loading && visibleItemCount + pastVisiblesItem >= totalItemCount - 2 && totalItemCount != 0 && !END_OF_FEED_ADDED && !pauseListening) {
            onLoadMoreListener?.onLoadMore()
            loading = true
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    fun setLoaded() {
        loading = false
    }

    fun setLoading() {
        loading = true
    }

    fun setEndOfScroll() {
        END_OF_FEED_ADDED = true
    }

    fun setFirstOfScroll() {
        loading = false
        END_OF_FEED_ADDED = false
    }
}