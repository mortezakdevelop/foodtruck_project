package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.data.model.ActivityModel
import com.texonapp.foodtruck.data.model.PromotionModel
import com.texonapp.foodtruck.databinding.ItemActivityBinding
import com.texonapp.foodtruck.databinding.ItemLoadingBinding
import com.texonapp.foodtruck.databinding.VouchersItemsBinding
import com.texonapp.foodtruck.model.VouchersModel
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM

class VouchersAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = ArrayList<PromotionModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                VouchersItemsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
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
        if (holder is VouchersAdapter.CustomHolder) {
            holder.bind((dataList[position] as PromotionModel))
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class CustomHolder(private val binding:VouchersItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(vouchersModel:PromotionModel) {

            binding.img.context?.let {

                val theImage = GlideUrl(
                    "https://emenu.streetfooddynasty.com/promotions/default.jpg", LazyHeaders.Builder()
                        .addHeader("User-Agent", "5")
                        .build()
                )

                Glide.with(it)
                    .load(theImage)
                    //.placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.img)
            }

            binding.title.text = vouchersModel.text

        }
    }

    fun addAll(list: ArrayList<PromotionModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }
}