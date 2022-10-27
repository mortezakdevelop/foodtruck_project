package com.texonapp.foodtruck.adapter.base

import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.databinding.ItemLoadingBinding

class LoadingViewHolder(private val binding: ItemLoadingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.progressBar.isIndeterminate = true
    }
}