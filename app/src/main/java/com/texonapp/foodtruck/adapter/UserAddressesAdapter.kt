package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.ItemUserAddressBinding
import com.texonapp.foodtruck.model.UserAddressModel
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity


class UserAddressesAdapter(private val data: ArrayList<UserAddressDialogEntity>) :
    RecyclerView.Adapter<UserAddressesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemUserAddressBinding: ItemUserAddressBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user_address, parent, false
        )
        return ViewHolder(itemUserAddressBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val binding: ItemUserAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userAddressDialogEntity: UserAddressDialogEntity) {
//            binding.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, binding.swipeLeft)
//            binding.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, binding.swipeRight);
            binding.address.text = userAddressDialogEntity.userAddressModel.address
            binding.executePendingBindings()
        }
    }
}


//class UserAddressesAdapter(private var data: ArrayList<UserAddressDialogEntity>) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//

//    private val dataList = ArrayList<Any?>()
//    var listener: AdapterListener? = null
//    var selectedPosition = -1
//
//    override fun getItemViewType(position: Int): Int {
//        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if (viewType == VIEW_TYPE_ITEM) {
//            val binding =
//                ItemUserAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
//            holder.bind(position, (dataList[position] as UserAddressModel))
//        } else if (holder is LoadingViewHolder) {
//            holder.bind()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size
//    }
//
//    fun addAll(list: ArrayList<UserAddressModel>) {
//        dataList.addAll(list)
//        notifyItemRangeInserted(0, list.size)
//
//    }
//
//    private fun itemSelected(selectedPosition: Int) {
//        if (this.selectedPosition == selectedPosition)
//            return
//        if (this.selectedPosition != -1) {
//            (dataList[this.selectedPosition] as UserAddressModel).default = false
//            notifyItemChanged(this.selectedPosition)
//        }
//        (dataList[selectedPosition] as UserAddressModel).default = true
//        notifyItemChanged(selectedPosition)
//    }
//
//    inner class CustomHolder(val binding: ItemUserAddressBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(position: Int, model: UserAddressModel) {
//            binding.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, binding.swipeLeft)
//            binding.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, binding.swipeRight);
//            binding.address.text = model.address
//            binding.background.isSelected = model.default
//            binding.checkBox.isSelected = model.default
//            if (model.default)
//                selectedPosition = position
//            binding.background.setOnClickListener {
//                listener?.onItemClick(position, model)
//                itemSelected(position)
//            }
//        }
//    }
//}