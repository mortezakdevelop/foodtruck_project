package com.texonapp.foodtruck.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.CategoryItemBinding
import com.texonapp.foodtruck.model.CategoryModel

class CategoryAdapter(private var data: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolder {
        val categoryItemBinding: CategoryItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.category_item, parent, false
        )
        return CategoryViewHolder(categoryItemBinding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CategoryViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryModel: CategoryModel) {
            binding.cvCategoryItem.strokeColor = Color.parseColor(categoryModel.colorStroke)
            binding.cvCategoryItem.strokeWidth = 2
            binding.clCategoryItem.setBackgroundColor(Color.parseColor(categoryModel.color))
            binding.tvTitle.text = categoryModel.title
            binding.executePendingBindings()
        }
    }
}