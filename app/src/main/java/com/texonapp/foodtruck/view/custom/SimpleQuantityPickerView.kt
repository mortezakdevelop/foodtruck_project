package com.texonapp.foodtruck.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.CustomSimpleQuantityViewBinding

class SimpleQuantityPickerView : RelativeLayout {
    var listener: QuantityPickerListener? = null

    var quantity: Int = 0
        set(value) {
            binding.quantity.text = value.toString()
            field = value
        }


    fun setListeners(listener: QuantityPickerListener) {
        this.listener = listener
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    lateinit var binding: CustomSimpleQuantityViewBinding

    private fun init(context: Context, attributeSet: AttributeSet?) {
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.custom_simple_quantity_view,
                this,
                true
            )
        initListeners()
    }

    private fun initListeners() {
        binding.addQuantity.setOnClickListener {
            val old: Int = quantity
            val new: Int = old + 1
            listener?.increaseQuantity(old, new)
            setValue(new)
        }

        binding.minusQuantity.setOnClickListener {
            val old: Int = binding.quantity.text.toString().toInt()
            val new: Int = old - 1
            if (new < 0) return@setOnClickListener
            listener?.reduceFoodQuantity(old, new)
            setValue(new)
        }
    }

    fun setValue(new: Int) {
        quantity = new
    }

}
