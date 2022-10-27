package com.texonapp.foodtruck.view.profileFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.adapter.PaymentMethodsAdapter
import com.texonapp.foodtruck.databinding.FragmentPaymentMethodBinding
import com.texonapp.foodtruck.model.PaymentMethodModel
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment

class PaymentMethodsFragment : BaseFragment() {
    private lateinit var binding: FragmentPaymentMethodBinding
    private val adapter: PaymentMethodsAdapter by lazy {
        PaymentMethodsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        initialData()
    }

    private fun initialData() {
        initRecycler()
        adapter.addAll(getPaymentMethods())
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.listener = object : AdapterListener {
            override fun onItemClick(position: Int, obj: Any) {

            }
        }
    }

    private fun getPaymentMethods(): ArrayList<PaymentMethodModel> {
        val data = ArrayList<PaymentMethodModel>()
        data.add(
            PaymentMethodModel(
                "2121 6352 8465 ****",
                ""
            )
        )
        data.add(
            PaymentMethodModel(
                "2121 6352 8465 ****",
                ""
            )
        )
        data.add(
            PaymentMethodModel(
                "2121 6352 8465 ****",
                ""
            )
        )
        return data
    }
}