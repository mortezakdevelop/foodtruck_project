package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.data.model.InternalModel
import com.texonapp.foodtruck.databinding.ItemHomeSliderBinding
import com.texonapp.foodtruck.util.publicTools.AdapterListener

class SliderImageAdapter :
    RecyclerView.Adapter<SliderImageAdapter.SliderViewHolder>() {

    private val dataList = ArrayList<InternalModel>()
    var listener: AdapterListener? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        val binding =
            ItemHomeSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(position, dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addAll(list: ArrayList<InternalModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(0, list.size)

    }

    inner class SliderViewHolder(private var binding: ItemHomeSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, model: InternalModel) {
            binding.title.text = model.text
            binding.img.context?.let {
                Glide.with(it)
                    .load(model.image)
                    .placeholder(R.drawable.bg_card_round_10sdp)
                    .error(R.drawable.bg_card_round_10sdp)
                    .into(binding.img)
            }

//            binding.buyNow.setOnClickListener {
//                listener?.onItemClick(position, model)
//            }
        }
    }
}