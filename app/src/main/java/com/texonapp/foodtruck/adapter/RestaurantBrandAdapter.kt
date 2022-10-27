package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.base.LoadingHorizontalViewHolder
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.adapter.cardListener.HomeCardClickListener
import com.texonapp.foodtruck.data.model.RestaurantModel
import com.texonapp.foodtruck.databinding.ItemLoadingHorizontalBinding
import com.texonapp.foodtruck.databinding.ItemRestaurantBrandBinding
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING
import com.texonapp.foodtruck.util.publicTools.AdapterListener

class RestaurantBrandAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = ArrayList<Any?>()
    var listener: AdapterListener? = null
    var homeListener:HomeCardClickListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                ItemRestaurantBrandBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            CustomHolder(binding)
        } else {
            val binding =
                ItemLoadingHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            LoadingHorizontalViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CustomHolder) {
            holder.bind(position, (dataList[position] as RestaurantModel),homeListener)
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount() = dataList.size

    inner class CustomHolder(private val binding: ItemRestaurantBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: RestaurantModel,homeListener: HomeCardClickListener?) {
            binding.txtName.text = model.name
            binding.txtDistance.text = model.distance
            binding.logo.context?.let {
                val theImage = GlideUrl(
                    "https://emenu.streetfooddynasty.com/restaurants/logos/default.png", LazyHeaders.Builder()
                        .addHeader("User-Agent", "5")
                        .build()
                )
                Glide.with(it)
                    .load(theImage)
                    //.placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.logo)
            }
            binding.clHomeItem.setOnClickListener {
                homeListener?.onItemHomeClickListener(model)
            }
        }
    }

    fun addNull() {
        dataList.add(null)
        notifyItemInserted(itemCount - 1)
    }

    fun removeNull() {
        if (itemCount == 0) return
        val lastPos = itemCount - 1
        if (dataList[lastPos] == null) {
            dataList.removeAt(lastPos)
            notifyItemRemoved(lastPos)
        }
    }

    fun addAll(list: ArrayList<RestaurantModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }

    fun clear() {
        val size = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, size)
    }
}