package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.MutlyBrandRvItemBinding
import com.texonapp.foodtruck.databinding.PopularFoodItemBinding
import com.texonapp.foodtruck.model.PopularFoodModel

class PopularFoodAdapter(private val data:ArrayList<PopularFoodModel>):RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularFoodAdapter.PopularFoodViewHolder {
        val popularFoodItemBinding: PopularFoodItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.popular_food_item, parent, false
        )
        return PopularFoodViewHolder(popularFoodItemBinding)    }

    override fun onBindViewHolder(holder: PopularFoodAdapter.PopularFoodViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PopularFoodViewHolder(val binding:PopularFoodItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(popularFoodModel: PopularFoodModel) {

            binding.ivPopularFood.setImageResource(R.drawable.popular_food_item_image)
            binding.tvTitleSpacy.text = popularFoodModel.title
            binding.subtitleSpacy.text = popularFoodModel.subTitle
            binding.tvPrice.text = popularFoodModel.price
            binding.executePendingBindings()

        }

    }
}