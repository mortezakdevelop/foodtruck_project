package com.texonapp.foodtruck.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.MultyBrandAdapter
import com.texonapp.foodtruck.adapter.TypeOrderAdapter
import com.texonapp.foodtruck.adapter.cardListener.MultyCardClickListener
import com.texonapp.foodtruck.adapter.cardListener.OrderTypeClickListener
import com.texonapp.foodtruck.databinding.FragmentMultyBrandBinding
import com.texonapp.foodtruck.model.BrandRestaurantModel
import com.texonapp.foodtruck.model.DeliveryTypeModel
import com.texonapp.foodtruck.mvi.BrandState
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.BrandViewModel


class MultyBrandsFragment : BaseFragment(), OrderTypeClickListener {

    lateinit var fragmentMultyBrandsFragmentBinding: FragmentMultyBrandBinding
    private val viewModel: BrandViewModel by viewModels()

    private val dataOrder: ArrayList<DeliveryTypeModel> = ArrayList()
    val data: ArrayList<BrandRestaurantModel> = ArrayList()

    private val multyBrandAdapter by lazy {
        MultyBrandAdapter(data, this)
    }

    private val adapter: TypeOrderAdapter by lazy {
        TypeOrderAdapter(dataOrder, this)
    }

    override fun listeners() {
        initRestaurantBrandRecycler()
        //initialData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
        viewModel.getBrand()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        fragmentMultyBrandsFragmentBinding =
            FragmentMultyBrandBinding.inflate(layoutInflater, container, false)
        setupOrderTypeAdapter()

        fragmentMultyBrandsFragmentBinding.included.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }


        return fragmentMultyBrandsFragmentBinding.root
    }

//    private fun initialData() {
//        initAdapter()
//        adapter.addAll(setupOrderTypeAdapter())
//    }

    private fun setupOrderTypeAdapter(){
        val data: ArrayList<DeliveryTypeModel> = ArrayList()
        data.add(DeliveryTypeModel("Delivery"))
        data.add(DeliveryTypeModel("Order"))
        data.add(DeliveryTypeModel("Model"))

        fragmentMultyBrandsFragmentBinding.rvOrderType.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        fragmentMultyBrandsFragmentBinding.rvOrderType.adapter = TypeOrderAdapter(data,this )
    }


//    private fun setupMultyBrandAdapter(){
//        val data: ArrayList<RestaurantBrandModel> = ArrayList()
//        data.add(RestaurantBrandModel(R.drawable.restaurant_company_recycler_one, "Vegan Resto","12 Mins"))
//        data.add(RestaurantBrandModel(R.drawable.restuarant_company_recycler_two, "Healty Food","8 Mins"))
//        data.add(RestaurantBrandModel(R.drawable.restaurant_company_recycler_one, "Vegan Resto","12 Mins"))
//        data.add(RestaurantBrandModel(R.drawable.restaurant_company_recycler_one, "Vegan Resto","12 Mins"))
//        data.add(RestaurantBrandModel(R.drawable.restaurant_company_recycler_one, "Vegan Resto","12 Mins"))
//        data.add(RestaurantBrandModel(R.drawable.restaurant_company_recycler_one, "Vegan Resto","12 Mins"))
//        data.add(RestaurantBrandModel(R.drawable.restaurant_company_recycler_one, "Vegan Resto","12 Mins"))
//        data.add(RestaurantBrandModel(R.drawable.restaurant_company_recycler_one, "Vegan Resto","12 Mins"))
//
//        fragmentMultyBrandsFragmentBinding.rvMultyBrand.layoutManager =
//            LinearLayoutManager(requireContext())
//        fragmentMultyBrandsFragmentBinding.rvMultyBrand.adapter = MultyBrandAdapter(data,this )
//    }

    private fun initRestaurantBrandRecycler() {
        val layoutManager = GridLayoutManager(
            context, 2,
            RecyclerView.VERTICAL, false
        )
        fragmentMultyBrandsFragmentBinding.rvMultyBrand.layoutManager = layoutManager
        fragmentMultyBrandsFragmentBinding.rvMultyBrand.adapter = multyBrandAdapter

//        fragmentMultyBrandsFragmentBinding.rvMultyBrand.layoutManager =
//            LinearLayoutManager(requireContext())
//        fragmentMultyBrandsFragmentBinding.rvMultyBrand.adapter = MultyBrandAdapter(data, this)
        multyBrandAdapter.listener = object : AdapterListener, MultyCardClickListener {
            override fun onItemRVClickListener(brandRestaurantModel: BrandRestaurantModel) {
                val bundle = Bundle()
                bundle.putString(BRAND_ID,brandRestaurantModel.id.toString())

                findNavController().navigate(R.id.action_multyBrandsFragment_to_brandPageFragment,bundle)

            }
        }
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is BrandState.GetBrands -> {
                    viewModel.setMainLoadingState(false)
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.responce?.let {
                        it.data?.let { it1 -> multyBrandAdapter.addAll(it1) }
                    }
                }
            }

            viewModel.stateOff()
        }
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is BrandState.GetBrands -> {
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.responce?.let {

                    }
                }
            }
        }
    }

//    private fun subscribeObserver() {
//        viewModel.dataState.observe(this) { brandState ->
//            when (brandState) {
//                is BrandState.GetBrands -> {
//                    viewModel.setMainLoadingState(true)
//                    brandState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//                    brandState.responce?.let {
//                        it.data?.let { it1 -> multyBrandAdapter.addAll(it1.restaurants?.data!!) }
//                    }
//                }
//            }
//
//            viewModel.stateOff()
//        }
//    }
}