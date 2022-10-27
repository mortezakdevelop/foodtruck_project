package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.cardListener.OrderTypeClickListener
import com.texonapp.foodtruck.databinding.OrderTypeMultyBrandItemBinding
import com.texonapp.foodtruck.model.DeliveryTypeModel
import com.texonapp.foodtruck.model.PaymentMethodModel

class TypeOrderAdapter(private val data:ArrayList<DeliveryTypeModel>,private val listener:OrderTypeClickListener):RecyclerView.Adapter<TypeOrderAdapter.ViewHolder>() {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeOrderAdapter.ViewHolder {
        val orderTypeMultyBrandItemBinding: OrderTypeMultyBrandItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.order_type_multy_brand_item,
                parent,
                false
            )
        return ViewHolder(orderTypeMultyBrandItemBinding)
    }

    override fun onBindViewHolder(holder: TypeOrderAdapter.ViewHolder, position: Int) {
        holder.bind(position,data[position], listener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(list: ArrayList<DeliveryTypeModel>) {
        notifyItemRangeInserted(3, list.size)
    }


    private fun itemSelected(selectedPosition: Int) {
        if (this.selectedPosition == selectedPosition)
            return
        if (this.selectedPosition != -1){
            data[this.selectedPosition].activated = false
            notifyItemChanged(this.selectedPosition)
        }
        data[selectedPosition].activated = true
        notifyItemChanged(selectedPosition)
    }

    inner class ViewHolder(private val binding: OrderTypeMultyBrandItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int,orderModel: DeliveryTypeModel, listener: OrderTypeClickListener) {
            // best practice for text view(bind textview)
            binding.tvOrderType.setTextFuture(
                PrecomputedTextCompat.getTextFuture(
                    orderModel.orderType,
                    binding.tvOrderType.textMetricsParamsCompat,
                    null
                )
            )

            binding.tvOrderType.isSelected = orderModel.activated
            if (orderModel.activated)
                selectedPosition = position
            binding.cvItem.setOnClickListener {
                listener?.onItemOrderTypeClickListener(position, orderModel)
                itemSelected(position)
            }

            binding.executePendingBindings()
        }
    }
}