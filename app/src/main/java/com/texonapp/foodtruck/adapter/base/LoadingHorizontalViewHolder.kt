package com.texonapp.foodtruck.adapter.base

import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.databinding.ItemLoadingHorizontalBinding

class LoadingHorizontalViewHolder(private val binding: ItemLoadingHorizontalBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.progressBar.isIndeterminate = true
    }
}