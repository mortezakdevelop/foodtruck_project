package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.data.model.OrderModel
import com.texonapp.foodtruck.databinding.ItemCartOrderBinding
import com.texonapp.foodtruck.util.CurrencyUtil
import com.texonapp.foodtruck.view.custom.QuantityPickerListener

class CartOrdersAdapter : RecyclerView.Adapter<CartOrdersAdapter.CustomHolder>() {

    private val dataList = ArrayList<OrderModel>()
    var listener: CartOrderListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val binding =
            ItemCartOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        holder.bind(position, dataList[position])
    }

    override fun getItemCount() = dataList.size

    fun getItems():ArrayList<OrderModel>{
        return dataList
    }

    inner class CustomHolder(private val binding: ItemCartOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: OrderModel) {
            binding.title.text = model.food?.name
            binding.description.text = model.food?.description
            binding.price.text = CurrencyUtil.splitPriceWithStartCurrency(model.food?.price?.toDouble())
            binding.quantityPicker.setValue(model.quantity ?: 0)

            binding.img.context?.let {
                Glide.with(it)
                    .load(model.food?.img)
                    .placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.img)
            }

            binding.quantityPicker.listener = object : QuantityPickerListener {
                override fun increaseQuantity(oldValue: Int, newValue: Int) {
                    listener?.increaseQuantity(position, model, newValue)
                }

                override fun reduceFoodQuantity(oldValue: Int, newValue: Int) {
                    if (newValue == 0)
                        listener?.removeItem(position, model)
                    else
                        listener?.reduceQuantity(position, model, newValue)
                }
            }

            binding.delete.setOnClickListener {
                listener?.removeItem(position, model)
            }
        }
    }

    fun addAll(list: ArrayList<OrderModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }

    fun clear() {
        val size = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun changeItemQuantity(position: Int, quantity: Int) {
        dataList[position].quantity = quantity
        notifyItemChanged(position)
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, (itemCount - position) + 1)
    }

    interface CartOrderListener {
        fun increaseQuantity(position: Int, obj: OrderModel, newValue: Int) {}
        fun reduceQuantity(position: Int, obj: OrderModel, newValue: Int) {}
        fun removeItem(position: Int, obj: OrderModel) {}
    }
}