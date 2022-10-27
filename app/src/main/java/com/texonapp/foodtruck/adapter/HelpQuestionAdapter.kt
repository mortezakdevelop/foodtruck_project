package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.databinding.ItemHelpQuestionBinding
import com.texonapp.foodtruck.model.HelpQuestionModel
import com.texonapp.foodtruck.util.publicTools.AdapterListener

class HelpQuestionAdapter :
    RecyclerView.Adapter<HelpQuestionAdapter.CustomHolder>() {

    private val dataList = ArrayList<HelpQuestionModel>()
    var listener: AdapterListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HelpQuestionAdapter.CustomHolder {
        val binding =
            ItemHelpQuestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CustomHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpQuestionAdapter.CustomHolder, position: Int) {
        holder.bind(position, dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addAll(list: ArrayList<HelpQuestionModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(0, list.size)

    }

    inner class CustomHolder(val binding: ItemHelpQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, model: HelpQuestionModel) {
            binding.title.text = model.title
            binding.description.text = model.description
            binding.background.isSelected = model.activated
            binding.arrow.isSelected = model.activated
            if (model.activated)
                binding.description.maxLines = Integer.MAX_VALUE
            else
                binding.description.maxLines = 1

            binding.background.setOnClickListener {
                listener?.onItemClick(position, model)
                dataList[position].activated = !binding.arrow.isSelected
                notifyItemChanged(position)
            }
        }
    }
}