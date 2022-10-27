package com.texonapp.foodtruck.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.base.LoadingViewHolder
import com.texonapp.foodtruck.data.model.ActivityModel
import com.texonapp.foodtruck.databinding.ItemActivityBinding
import com.texonapp.foodtruck.databinding.ItemLoadingBinding
import com.texonapp.foodtruck.util.VIEW_TYPE_ITEM
import com.texonapp.foodtruck.util.VIEW_TYPE_LOADING

class ActivitiesAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = ArrayList<Any?>()
    var listener: ActivityAdapterListener? = null


    override fun getItemViewType(position: Int): Int {
        return if (dataList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val binding =
                ItemActivityBinding.inflate(
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
            holder.bind(position, (dataList[position] as ActivityModel))
        } else if (holder is LoadingViewHolder) {
            holder.bind()
        }
    }

    override fun getItemCount() = dataList.size

    inner class CustomHolder(private val binding: ItemActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, model: ActivityModel) {
            binding.txtTitle.text = model.title
            binding.txtDescription.text = model.description

            binding.img.context?.let {
                val theImage = GlideUrl(
                    "https://emenu.streetfooddynasty.com/activities/default.jpg", LazyHeaders.Builder()
                        .addHeader("User-Agent", "5")
                        .build()
                )
                Glide.with(it)
                    .load(theImage)
                    //.placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default)
                    .into(binding.img)

            }

            binding.read.setOnClickListener {
                listener?.read(position, model)
            }

            // need test
                binding.share.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/share..."
                context.startActivity(Intent.createChooser(intent, "Share"))
                listener?.share(position, model)
                }



            binding.like.setOnClickListener {
                listener?.like(position, model)
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

    fun addAll(list: ArrayList<ActivityModel>) {
        dataList.addAll(list)
        notifyItemRangeInserted(dataList.size - list.size, list.size)
    }

    fun clear() {
        val size = itemCount
        dataList.clear()
        notifyItemRangeRemoved(0, size)
    }

    interface ActivityAdapterListener {
        fun read(position: Int, obj: ActivityModel) {}
        fun share(position: Int, obj: ActivityModel) {}
        fun like(position: Int, obj: ActivityModel) {}
    }
}