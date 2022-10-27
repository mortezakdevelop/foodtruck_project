package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.databinding.ItemLoadingBinding
import com.texonapp.foodtruck.databinding.ItemReOrderFoodBinding
import com.texonapp.foodtruck.model.FoodModel
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING
import com.texonapp.foodtruck.util.publicTools.AdapterListener

class ReOrderFoodAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = ArrayList<Any?>()
    var listener: AdapterListener? = null


    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                ItemReOrderFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CustomHolder(binding)
        } else {
            val binding =
                ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            LoadingViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CustomHolder) {
            holder.bind(position, (dataList[position] as FoodModel))
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }

    }

    override fun getItemCount() = dataList.size

    inner class CustomHolder(private val binding: ItemReOrderFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: FoodModel) {

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

    fun addAll(list: ArrayList<FoodModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }

    fun clear() {
        val size = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, size)
    }
}