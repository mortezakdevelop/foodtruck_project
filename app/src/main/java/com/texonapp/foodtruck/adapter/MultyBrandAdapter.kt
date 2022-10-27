package com.texonapp.foodtruck.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.adapter.cardListener.MultyCardClickListener
import com.texonapp.foodtruck.data.model.ActivityModel
import com.texonapp.foodtruck.databinding.ItemLoadingBinding
import com.texonapp.foodtruck.databinding.MutlyBrandRvItemBinding
import com.texonapp.foodtruck.model.BrandRestaurantModel
import com.texonapp.foodtruck.model.DataBrandModel
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING
import com.texonapp.foodtruck.view.home.MultyBrandsFragment


class MultyBrandAdapter(
    data: ArrayList<BrandRestaurantModel>,
    multyBrandsFragment: MultyBrandsFragment,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    private val dataList = ArrayList<Any?>()
    var listener: MultyCardClickListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                MutlyBrandRvItemBinding.inflate(
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
            holder.bind(position, (dataList[position] as BrandRestaurantModel),listener!!)
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount() = dataList.size

    inner class CustomHolder(private val binding: MutlyBrandRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: BrandRestaurantModel,listener: MultyCardClickListener) {
            binding.tvImage.text = model.name
            binding.tvMinute.text = model.name

            binding.ivImage.context?.let {
                val theImage = GlideUrl(
                    "https://emenu.streetfooddynasty.com/restaurants/logos/default.png", LazyHeaders.Builder()
                        .addHeader("User-Agent", "5")
                        .build()
                )
                Glide.with(it)
                    .load(theImage)
                    //.placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.ivImage)

            }

            binding.clItem.setOnClickListener {
                listener.onItemRVClickListener(model)
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

    fun addAll(list: ArrayList<BrandRestaurantModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }

    fun clear() {
        val size = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, size)
    }

    interface ActivityAdapterListener {
        fun click(position: Int, obj: BrandRestaurantModel) {}

    }
}


////class MultyBrandAdapter(context: Context, courseModelArrayList: ArrayList<RestaurantBrandModel?>?) :
////    ArrayAdapter<RestaurantBrandModel?>(context, 0, courseModelArrayList!!) {
////    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
////        var listitemView: View? = convertView
////        if (listitemView == null) {
////            // Layout Inflater inflates each item to be displayed in GridView.
////            listitemView = LayoutInflater.from(context).inflate(R.layout.mutly_brand_rv_item, parent, false)
////        }
////        val restaurantBrandModel: RestaurantBrandModel? = getItem(position)
////        val courseTV: TextView = listitemView!!.findViewById(R.id.iv_image)
////        val courseIV: ImageView = listitemView.findViewById(R.id.tv_image)
////
////        courseTV.text = restaurantBrandModel!!.restaurantName
////        with(courseIV) {
////
////            courseTV.text = restaurantBrandModel.restaurantName
////            setImageResource(restaurantBrandModel.img as Int)
////        }
////        return listitemView
////    }
////}
//
//class MultyBrandAdapter(
//    private var data: ArrayList<BrandRestaurantModel>,
//    var listener: MultyCardClickListener
//) : RecyclerView.Adapter<MultyBrandAdapter.MultyBrandViewHolder>() {
//    private val dataList = ArrayList<Any?>()
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): MultyBrandAdapter.MultyBrandViewHolder {
//        val multyBrandItemBinding: MutlyBrandRvItemBinding = DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context), R.layout.mutly_brand_rv_item, parent, false
//        )
//        return MultyBrandViewHolder(multyBrandItemBinding)
//    }
//
//    override fun onBindViewHolder(holder: MultyBrandAdapter.MultyBrandViewHolder, position: Int) {
//        holder.bind(data[position], listener)
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    inner class MultyBrandViewHolder(val binding: MutlyBrandRvItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(brandRestaurantModel: BrandRestaurantModel, listener: MultyCardClickListener) {
//            binding.ivImage.setImageResource(R.drawable.restaurant_company_recycler_one)
//            binding.tvImage.text = brandRestaurantModel.name
//            binding.tvMinute.text = brandRestaurantModel.description
//
//            binding.clItem.setOnClickListener {
//                listener.onItemRVClickListener(brandRestaurantModel)
//            }
//
//            binding.executePendingBindings()
//
//        }
//    }
//
//    fun addAll(list: ArrayList<BrandRestaurantModel>) {
//        dataList.addAll(list)
//        notifyItemRangeInserted(dataList.size - list.size, list.size)
//    }
//}
