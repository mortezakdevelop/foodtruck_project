package com.texonapp.foodtruck.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.databinding.ItemLoadingBinding
import com.texonapp.foodtruck.databinding.ItemSuggestAddressBinding
import com.texonapp.foodtruck.databinding.ItemUserAddressBinding
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING
import com.texonapp.foodtruck.util.publicTools.AdapterListener

//class SuggestAddressAdapter :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private val dataList = ArrayList<Any?>()
//    var listener: AdapterListener? = null
//
//    override fun getItemViewType(position: Int): Int {
//        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if (viewType == VIEW_TYPE_ITEM) {
//            val binding =
//                ItemSuggestAddressBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            CustomHolder(binding)
//        } else {
//            val binding =
//                ItemLoadingBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            LoadingViewHolder(binding)
//        }
//
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is CustomHolder) {
//            holder.bind(position, (dataList[position] as SearchSuggestion))
//        } else if (holder is LoadingViewHolder) {
//            holder.bind()
//        }
//
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//
//    fun addAll(list: List<SearchSuggestion>) {
//        dataList.addAll(list)
//        notifyItemRangeInserted(0, list.size)
//    }
//
//    fun clearAll() {
//        dataList.clear()
//        notifyDataSetChanged()
//    }
//
//    inner class CustomHolder(val binding: ItemSuggestAddressBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        @SuppressLint("SetTextI18n")
//        fun bind(position: Int, model: SearchSuggestion) {
//            binding.title.text = model.address?.place
//            binding.distance.text = model.distanceMeters.toString()
//            binding.address.text =
//                model.address?.street + ", " + model.address?.region + ", " + model.address?.district
//            binding.selectArea.setOnClickListener {
//                listener?.onItemClick(position, model)
//            }
//            if (position == dataList.size - 1)
//                binding.separator.visibility = View.INVISIBLE
//            else
//                binding.separator.visibility = View.VISIBLE
//        }
//    }
//}