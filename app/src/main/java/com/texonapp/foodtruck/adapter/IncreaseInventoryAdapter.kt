package com.texonapp.foodtruck.adapter

import android.view.DragEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.ItemClickListener
import com.texonapp.foodtruck.databinding.IncreaseInventoryItemBinding
import com.texonapp.foodtruck.model.IncreaseInventoryModel
import com.texonapp.foodtruck.view.home.InventoryFragment

class IncreaseInventoryAdapter(private val data:ArrayList<IncreaseInventoryModel>, private var listener: ItemClickListener):RecyclerView.Adapter<IncreaseInventoryAdapter.IncreaseInventoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IncreaseInventoryAdapter.IncreaseInventoryViewHolder {
        val increaseInventoryItemBinding: IncreaseInventoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.increase_inventory_item, parent, false
        )
        return IncreaseInventoryViewHolder(increaseInventoryItemBinding)
    }


    override fun onBindViewHolder(
        holder: IncreaseInventoryAdapter.IncreaseInventoryViewHolder,
        position: Int
    ) {

        holder.bind(data[position],listener)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class IncreaseInventoryViewHolder(val binding:IncreaseInventoryItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(increaseInventoryModel: IncreaseInventoryModel, listener: ItemClickListener) {
            binding.tvPrice.text = increaseInventoryModel.price
            binding.executePendingBindings()
        }
    }
}