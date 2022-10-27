package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.base.LoadingHorizontalViewHolder
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.data.model.Food
import com.texonapp.foodtruck.databinding.BrandRecyclerPopularFoodItemBinding
import com.texonapp.foodtruck.databinding.ItemLoadingHorizontalBinding
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING
import com.texonapp.foodtruck.util.publicTools.AdapterListener


class BrandPopularFoodAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = ArrayList<Any?>()
    var listener: AdapterListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                BrandRecyclerPopularFoodItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            CustomHolder(binding)
        } else {
            val binding =
                ItemLoadingHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            LoadingHorizontalViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CustomHolder) {
            holder.bind(position, (dataList[position] as Food))
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount() = dataList.size

    inner class CustomHolder(private val binding: BrandRecyclerPopularFoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: Food) {
            binding.tvTitle.text = model.name
            binding.tvPrice.text = model.price

            binding.ivImage.context?.let {
                val theImage = GlideUrl(
                    "https://emenu.streetfooddynasty.com/restaurants/images/default.jpg", LazyHeaders.Builder()
                        .addHeader("User-Agent", "5")
                        .build()
                )
                Glide.with(it)
                    .load(theImage)
                    //.placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.ivImage)
            }
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

    fun addAll(list: ArrayList<Food>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }

    fun clear() {
        val size = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, size)
    }
}



//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.databinding.DataBindingUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.texonapp.foodtruck.R
//import com.texonapp.foodtruck.databinding.BrandRecyclerPopularFoodItemBinding
//import com.texonapp.foodtruck.model.BrandPopularFoodModel

//class BrandPopularFoodAdapter(private val model:ArrayList<BrandPopularFoodModel>):RecyclerView.Adapter<BrandPopularFoodAdapter.BrandPopularFoodViewHolder>() {
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): BrandPopularFoodAdapter.BrandPopularFoodViewHolder {
//        val brandRecyclerPopularFoodItemBinding:BrandRecyclerPopularFoodItemBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.brand_recycler_popular_food_item,parent,false)
//        return BrandPopularFoodViewHolder(brandRecyclerPopularFoodItemBinding)    }
//
//    override fun onBindViewHolder(
//        holder: BrandPopularFoodAdapter.BrandPopularFoodViewHolder,
//        position: Int
//    ) {
//        holder.bind(model[position])    }
//
//    override fun getItemCount(): Int {
//        return model.size
//    }
//
//    inner class BrandPopularFoodViewHolder(val binding:BrandRecyclerPopularFoodItemBinding):RecyclerView.ViewHolder(binding.root){
//        fun bind(brandPopularFoodModel: BrandPopularFoodModel) {
//            binding.ivImage.setImageResource(R.drawable.pizza_brand_item_image_one)
//            binding.tvTitle.text= brandPopularFoodModel.title
//            binding.tvPrice.text = brandPopularFoodModel.price
//            binding.executePendingBindings()
//        }
//
//    }
//
//}