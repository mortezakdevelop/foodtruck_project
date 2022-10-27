package com.texonapp.foodtruck.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.CustomQuantityViewBinding

class QuantityPickerView : RelativeLayout {
    var listener: QuantityPickerListener? = null

    var quantity: Int = 0
        set(value) {
            binding.quantity.text = value.toString()
            setValueAppearance(value)
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

    lateinit var binding: CustomQuantityViewBinding

    private fun init(context: Context, attributeSet: AttributeSet?) {
        binding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.custom_quantity_view,
                this,
                true
            )
        attributeSet?.let {
            val attrs = context.obtainStyledAttributes(it, R.styleable.QuantityPickerView, 0, 0)
            try {
                val btnName = attrs.getString(R.styleable.QuantityPickerView_btn_name)
                binding.buy.text = btnName
            } finally {
                attrs.recycle()
            }
        }
        initListeners()
    }

    private fun initListeners() {
        binding.buy.setOnClickListener {
            val old: Int = quantity
            val new: Int = old + 1
            setValue(old, new)
        }

        binding.addQuantity.setOnClickListener {
            val old: Int = quantity
            val new: Int = old + 1
            listener?.increaseQuantity(old, new)
            setValue(old, new)
        }

        binding.minusQuantity.setOnClickListener {
            val old: Int = binding.quantity.text.toString().toInt()
            val new: Int = old - 1
            listener?.reduceFoodQuantity(old, new)
            setValue(old, new)
        }
    }

    private fun setValue(old: Int, new: Int) {
        quantity = new
    }

    private fun setValueAppearance(value: Int) {
        if (value > 0) {
            binding.buy.isEnabled = false
            binding.quantitySection.visibility = View.VISIBLE
        } else {
            binding.buy.isEnabled = true
            binding.quantitySection.visibility = View.GONE
        }
    }

}
