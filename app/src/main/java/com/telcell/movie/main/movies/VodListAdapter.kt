package com.telcell.movie.main.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.telcell.movie.R
import com.telcell.movie.misc.GridSpacingItemDecoration
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_vod_list.*

class VodListAdapter(var vodList: List<ListItem>, private val movieClickListener: MovieClickListener, private val columnCount: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var vodCornerRadius = 0

    override fun getItemViewType(position: Int): Int {
        return vodList[position].getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Type.VOD.ordinal -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vod_list, parent, false)
                VodViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_loading, parent, false)
                ProgressViewHolder(itemView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VodViewHolder) {
            if (vodCornerRadius == 0) {
                vodCornerRadius = holder.bannerImageView.resources.getDimensionPixelSize(R.dimen.vod_thumbnail_corner_radius)
            }

            val item = vodList[position] as Vod

            val movie = item.backendObj
            holder.bannerImageView.setOnClickListener { v: View? -> movieClickListener.onMovieClick(item) }
            holder.vodBannerNameTV.text = movie.title
            holder.vodBannerYearTV.text = movie.release_date

            Glide.with(holder.bannerImageView.context)
                .load(item.posterUrl)
                .transform(CenterCrop(), RoundedCorners(vodCornerRadius))
                .placeholder(R.drawable.shape_vod_thumbnail)
                .into(holder.bannerImageView)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        while (recyclerView.itemDecorationCount != 0) {
            recyclerView.removeItemDecorationAt(0)
        }
        val spacingHorizontal = recyclerView.resources.getDimensionPixelSize(R.dimen.vod_list_horizontal_spacing)
        val spacingVertical = recyclerView.resources.getDimensionPixelSize(R.dimen.vod_list_vertical_spacing)
        recyclerView.addItemDecoration(GridSpacingItemDecoration(columnCount, spacingHorizontal, spacingVertical))
    }

    override fun getItemCount() = vodList.size

    fun isItemProgress(position: Int) = vodList[position] is Progress

    fun setItemsWithAdd(itemsList: List<ListItem>, updatePosition: Int, count: Int) {
        vodList = itemsList
        notifyItemRangeInserted(updatePosition, count)
    }

    fun setItemsWithRemove(itemsList: List<ListItem>, updatePosition: Int, count: Int) {
        vodList = itemsList
        notifyItemRangeRemoved(updatePosition, count)
    }

    class VodViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

    class ProgressViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}