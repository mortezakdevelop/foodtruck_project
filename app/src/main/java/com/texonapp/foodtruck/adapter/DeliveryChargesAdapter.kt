package com.texonapp.foodtruck.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.data.model.DeliveryChargeModel
import com.texonapp.foodtruck.databinding.ItemDeliveryChargeBinding
import com.texonapp.foodtruck.util.CurrencyUtil

class DeliveryChargesAdapter :
    RecyclerView.Adapter<DeliveryChargesAdapter.CustomHolder>() {

    private val dataList = ArrayList<DeliveryChargeModel>()
    var selectedPosition = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeliveryChargesAdapter.CustomHolder {
        val binding =
            ItemDeliveryChargeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CustomHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryChargesAdapter.CustomHolder, position: Int) {
        holder.bind(position, dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addAll(list: ArrayList<DeliveryChargeModel>) {
        dataList.clear()
        notifyDataSetChanged()
        dataList.addAll(list)
        notifyItemRangeInserted(0, list.size)

    }

    private fun itemSelected(selectedPosition: Int) {
        if (this.selectedPosition == selectedPosition)
            return
        val prevSelectPosition = this.selectedPosition
        this.selectedPosition = selectedPosition
        notifyItemChanged(prevSelectPosition)
        notifyItemChanged(this.selectedPosition)
    }

    inner class CustomHolder(val binding: ItemDeliveryChargeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, model: DeliveryChargeModel) {
            binding.checkBox.isSelected = position == selectedPosition
            binding.txtName.text = model.name
            binding.txtDeliveryCharge.context.let {
                binding.txtDeliveryCharge.text =
                    it.getString(R.string.delivery_charge) +
                            CurrencyUtil.splitPriceWithStartCurrency(
                                model.deliveryCharge
                            )
            }

            binding.img.context?.let {
                Glide.with(it)
                    .load(model.img)
                    .placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.img)
            }

            binding.checkBox.setOnClickListener {
                itemSelected(position)
            }
        }
    }

    fun getItemSelected(): DeliveryChargeModel {
        return dataList[selectedPosition]
    }
}