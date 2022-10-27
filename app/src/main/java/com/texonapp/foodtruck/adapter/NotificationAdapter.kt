package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.MutlyBrandRvItemBinding
import com.texonapp.foodtruck.databinding.NotificationRvItemBinding
import com.texonapp.foodtruck.model.NotificationModel

class NotificationAdapter(private val data:ArrayList<NotificationModel>):RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationAdapter.NotificationViewHolder {
        val notificationRvItemBinding: NotificationRvItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.notification_rv_item, parent, false
        )
        return NotificationViewHolder(notificationRvItemBinding)
    }

    override fun onBindViewHolder(
        holder: NotificationAdapter.NotificationViewHolder,
        position: Int
    ) {
        holder.bind(data[position])    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class NotificationViewHolder(val binding:NotificationRvItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(notificationModel: NotificationModel) {
            binding.ivDollar.setImageResource(notificationModel.image)
            binding.tvTitleItem.text = notificationModel.title
            binding.tvDateItem.text = notificationModel.date
            binding.executePendingBindings()
        }
    }
}