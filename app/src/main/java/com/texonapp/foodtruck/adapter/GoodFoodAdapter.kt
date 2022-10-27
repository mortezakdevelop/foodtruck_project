package com.texonapp.foodtruck.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.data.model.*
import com.texonapp.foodtruck.data.model.Foods
import com.texonapp.foodtruck.databinding.GoodFoodRecyclerviewItemBinding
import com.texonapp.foodtruck.databinding.ItemLoadingBinding
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING
import com.texonapp.foodtruck.view.custom.QuantityPickerListener

class GoodFoodAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    private val dataList = ArrayList<Foods.Food>()
    private val dataListTwo = ArrayList<OrderModel?>()
    var listener: ActivityAdapterListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                GoodFoodRecyclerviewItemBinding.inflate(
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
        if (holder is CustomHolder) {
            holder.bind(position, (dataList[position] as Foods.Food),listener!!)
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount() = dataList.size

    inner class CustomHolder(private val binding: GoodFoodRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: Foods.Food,listener: ActivityAdapterListener) {
            binding.title.text = model.name
            binding.description.text = model.description
            binding.price.text = model.price

            binding.img.context?.let {
                val theImage = GlideUrl(
                    "https://emenu.streetfooddynasty.com/foods/default.png", LazyHeaders.Builder()
                        .addHeader("User-Agent", "5")
                        .build()
                )
                Glide.with(it)
                    .load(theImage)
                    //.placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.img)

            }

            binding.tvBuy.setOnClickListener {
                binding.tvBuy.visibility = View.INVISIBLE
                binding.quantityPicker.visibility = View.VISIBLE
                binding.quantityPicker.quantity = 1
                listener.buy(position,dataList[position])
                binding.quantityPicker.listener = object : QuantityPickerListener {
                    override fun increaseQuantity(oldValue: Int, newValue: Int) {
                        if(newValue > 0)
                            listener.increaseQuantity(position,model, newValue)
                    }
                    override fun reduceFoodQuantity(oldValue: Int, newValue: Int) {
                        if (newValue == 0) {
                            listener.removeItem(position, model)
                            binding.tvBuy.visibility = View.VISIBLE
                            binding.quantityPicker.visibility = View.INVISIBLE
                        }
                        else
                            listener.reduceQuantity(position, model, newValue)
                    }
                }
            }

//            binding.read.setOnClickListener {
//                listener?.read(position, model)
//            }

            //        binding.share.setOnClickListener {
//                val intent = Intent()
//                intent.setAction(Intent.ACTION_SEND)
//                intent.setType("text/plain")
//                context.startActivity(Intent.createChooser(intent, "Share"))
//                listener?.share(position, model)
            //      }

//            binding.like.setOnClickListener {
//                listener?.like(position, model)
//            }

        }
    }

//    fun addNull() {
//        dataList.add(null)
//        notifyItemInserted(itemCount - 1)
//    }

    fun removeNull() {
        if (itemCount == 0) return
        val lastPos = itemCount - 1
        if (dataList[lastPos] == null) {
            dataList.removeAt(lastPos)
            notifyItemRemoved(lastPos)
        }
    }

    fun addAll(list: ArrayList<Foods.Food>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }

    fun addAllTwo(list: ArrayList<OrderModel>){
        dataListTwo.addAll(list)
        notifyItemRangeInserted(dataListTwo.size - list.size , list.size)
    }

    fun clear() {
        val size = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun changeItemQuantity(position: Int, quantity: Int) {
        dataListTwo[position]?.quantity = quantity
        notifyItemChanged(position)
    }

    interface ActivityAdapterListener {
        fun read(position: Int, obj: ActivityModel) {}
        fun share(position: Int, obj: ActivityModel) {}
        fun like(position: Int, obj: ActivityModel) {}
        fun buy(position: Int,obj: Foods.Food) {}
        fun removeItem(position: Int, obj: Foods.Food) {}
        fun increaseQuantity(position: Int, obj: Foods.Food, newValue: Int) {}
        fun reduceQuantity(position: Int, obj: Foods.Food , newValue: Int) {}
    }
}


//class GoodFoodAdapter(private val data:ArrayList<GoodFoodModel>):RecyclerView.Adapter<GoodFoodAdapter.GoodFoodViewHolder>() {
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): GoodFoodAdapter.GoodFoodViewHolder {
//        val goodFoodRecyclerviewItemBinding:GoodFoodRecyclerviewItemBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context), R.layout.good_food_recyclerview_item,parent,false)
//        return GoodFoodViewHolder(goodFoodRecyclerviewItemBinding)
//    }
//
//    override fun onBindViewHolder(holder: GoodFoodAdapter.GoodFoodViewHolder, position: Int) {
//        holder.bind(data[position])
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    inner class GoodFoodViewHolder(val binding:GoodFoodRecyclerviewItemBinding):RecyclerView.ViewHolder(binding.root){
//        fun bind(goodFoodModel: GoodFoodModel) {
//            binding.ivFood.setImageResource(R.drawable.good_food_menu_image)
//            binding.tvFoodName.text = goodFoodModel.nameFood
//            binding.tvRestaurantName.text = goodFoodModel.restaurantName
//            binding.tvPrice.text = goodFoodModel.price
//            binding.tvBuy.text = goodFoodModel.buy
//            binding.executePendingBindings()
//        }
//
//    }
//}