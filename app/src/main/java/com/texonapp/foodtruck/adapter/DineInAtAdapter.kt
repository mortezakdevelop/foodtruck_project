package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.databinding.ItemDineAtBinding
import com.texonapp.foodtruck.databinding.ItemLoadingBinding
import com.texonapp.foodtruck.databinding.ItemUserAddressBinding
import com.texonapp.foodtruck.model.DineAtModel
import com.texonapp.foodtruck.model.UserAddressModel
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING
import com.texonapp.foodtruck.util.publicTools.AdapterListener

class DineInAtAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = ArrayList<Any?>()
    var listener: AdapterListener? = null
    var selectedPosition = -1

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                ItemDineAtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            holder.bind(position, (dataList[position] as DineAtModel))
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addAll(list: ArrayList<DineAtModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(0, list.size)

    }

    private fun itemSelected(selectedPosition: Int) {
        if (this.selectedPosition == selectedPosition)
            return
        if (this.selectedPosition != -1) {
            (dataList[this.selectedPosition] as DineAtModel).default = false
            notifyItemChanged(this.selectedPosition)
        }
        (dataList[selectedPosition] as DineAtModel).default = true
        notifyItemChanged(selectedPosition)
    }

    inner class CustomHolder(val binding: ItemDineAtBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, model: DineAtModel) {
            binding.tvFoodType.text = model.foodType
            binding.tvFoodTime.text = model.foodTime
            binding.checkBox.isSelected = model.default
            if (model.default)
                selectedPosition = position
            binding.checkBox.setOnClickListener {
                listener?.onItemClick(position, model)
                itemSelected(position)
            }
        }
    }
}