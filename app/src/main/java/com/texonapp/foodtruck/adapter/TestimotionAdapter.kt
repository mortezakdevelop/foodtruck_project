package com.texonapp.foodtruck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.TestimonialsRvItemBinding
import com.texonapp.foodtruck.model.TestimonialModel


//class TestimotionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private val dataList = ArrayList<Any?>()
//    var listener: AdapterListener? = null
//
//    override fun getItemViewType(position: Int): Int {
//        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if (viewType == VIEW_TYPE_ITEM) {
//            val binding =
//                TestimonialsRvItemBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            CustomHolder(binding)
//        } else {
//            val binding =
//                ItemLoadingHorizontalBinding.inflate(
//                    LayoutInflater.from(parent.context),
//                    parent,
//                    false
//                )
//            LoadingHorizontalViewHolder(binding)
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is CustomHolder) {
//            holder.bind(position, (dataList[position] as Testimations))
//        } else if (holder is LoadingViewHolder) {
//            holder.bind()
//        }
//    }
//
//    override fun getItemCount() = dataList.size
//
//    inner class CustomHolder(private val binding: TestimonialsRvItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(position: Int, model: Testimations) {
//            binding.tvName = model.name
//            binding.tvSubtitleName.text = model.created_at
//            binding.tvDescription.text = model.body
//
//            binding.ivProfile.context?.let {
//                Glide.with(it)
//                    .load(model.image)
//                    .placeholder(R.drawable.image_default)
//                    .error(R.drawable.image_default)
//                    .into(binding.ivProfile)
//            }
//        }
//    }
//
//    fun addNull() {
//        dataList.add(null)
//        notifyItemInserted(itemCount - 1)
//    }
//
//    fun removeNull() {
//        if (itemCount == 0) return
//        val lastPos = itemCount - 1
//        if (dataList[lastPos] == null) {
//            dataList.removeAt(lastPos)
//            notifyItemRemoved(lastPos)
//        }
//    }
//
//    fun addAll(list: ArrayList<TestimonialModel>) {
//        dataList.addAll(list)
//        notifyItemRangeInserted(dataList.size - list.size, list.size)
//    }
//
//    fun clear() {
//        val size = itemCount
//        dataList.clear()
//        notifyItemRangeRemoved(0, size)
//    }
//}










//
class TestimotionAdapter(private var data:ArrayList<TestimonialModel>):RecyclerView.Adapter<TestimotionAdapter.TestimotionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TestimotionAdapter.TestimotionViewHolder {
        val testimonialsRvItemBinding: TestimonialsRvItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.testimonials_rv_item, parent, false
        )
        return TestimotionViewHolder(testimonialsRvItemBinding)
    }

    override fun onBindViewHolder(holder: TestimotionAdapter.TestimotionViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TestimotionViewHolder(val binding:TestimonialsRvItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(testimotionModel: TestimonialModel) {
            binding.ivProfile.setImageResource(R.drawable.good_food_menu_image)
            binding.tvName.text = testimotionModel.name
            binding.tvSubtitleName.text = testimotionModel.date
            binding.tvDescription.text = testimotionModel.description
            binding.executePendingBindings()
        }
    }
}