package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.databinding.ItemPaymentMethodBinding
import com.texonapp.foodtruck.model.PaymentMethodModel
import com.texonapp.foodtruck.util.publicTools.AdapterListener

class PaymentMethodsAdapter :
    RecyclerView.Adapter<PaymentMethodsAdapter.CustomHolder>() {

    private val dataList = ArrayList<PaymentMethodModel>()
    var listener: AdapterListener? = null
    var selectedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PaymentMethodsAdapter.CustomHolder {
        val binding =
            ItemPaymentMethodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CustomHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentMethodsAdapter.CustomHolder, position: Int) {
        holder.bind(position, dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addAll(list: ArrayList<PaymentMethodModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(0, list.size)

    }

    private fun itemSelected(selectedPosition: Int) {
        if (this.selectedPosition == selectedPosition)
            return
        if (this.selectedPosition != -1){
            dataList[this.selectedPosition].activated = false
            notifyItemChanged(this.selectedPosition)
        }
        dataList[selectedPosition].activated = true
        notifyItemChanged(selectedPosition)
    }

    inner class CustomHolder(val binding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, model: PaymentMethodModel) {
            binding.cartNumber.text = model.cartNumber
            binding.background.isSelected = model.activated
            binding.arrow.isSelected = model.activated
            if (model.activated)
                selectedPosition = position
            binding.background.setOnClickListener {
                listener?.onItemClick(position, model)
                itemSelected(position)
            }
        }
    }
}