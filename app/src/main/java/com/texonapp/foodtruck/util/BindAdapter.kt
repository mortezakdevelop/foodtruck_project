package com.texonapp.foodtruck.util


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.texonapp.foodtruck.R

@BindingAdapter("setImage")
fun setImage(view: AppCompatImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(imageUrl)
            .error(R.drawable.image_default)
            .placeholder(R.drawable.image_default)
            .into(view)
    }
}



