package com.texonapp.foodtruck.view.brandFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.GoodFoodAdapter
import com.texonapp.foodtruck.adapter.GoodFoodAdapter.*
import com.texonapp.foodtruck.data.model.Foods
import com.texonapp.foodtruck.data.model.OrderModel
import com.texonapp.foodtruck.data.model.RestaurantModel
import com.texonapp.foodtruck.databinding.FragmentFoodMenuBrandBinding
import com.texonapp.foodtruck.mvi.BrandState
import com.texonapp.foodtruck.mvi.state.CartOrderState
import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.BrandViewModel
import com.texonapp.foodtruck.viewmodel.CartOrderViewModel
import com.texonapp.foodtruck.viewmodel.GoodFoodMenuViewModel

class FoodMenuBrandFragment : BaseFragment() {
    private  lateinit var id:String
    private lateinit var bundle: Bundle
    private val model:BrandViewModel by viewModels()
    val cart:CartOrderViewModel by viewModels()
    lateinit var fragmentFoodMenuBrandBinding: FragmentFoodMenuBrandBinding
    private val viewModel: BrandViewModel by viewModels()
//    private val goodFoodViewModel: GoodFoodMenuViewModel by viewModels()

    private val goodFoodAdapter by lazy {
        GoodFoodAdapter()
    }
    private var itemChangedQuantity: ItemChangedQuantityFood? = null

    override fun listeners() {
        initGoodFoodRecycler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
        subscribeObserverTwo()
        bundle = arguments?:Bundle()
        viewModel.getBrandFood(1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentFoodMenuBrandBinding =
            FragmentFoodMenuBrandBinding.inflate(layoutInflater, container, false)
        // setupRecyclerView()
        id = bundle.getString(BRAND_ID,"0")
        Log.i("TAG","BRAND ID D:$id")
        model.getBrandFood(id.toInt())
        fragmentFoodMenuBrandBinding.included.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        fragmentFoodMenuBrandBinding.shoppingCart.setOnClickListener {
            findNavController().navigate(R.id.action_foodMenuBrandFragment_to_cartFragment)
        }
        return fragmentFoodMenuBrandBinding.root
    }

    private fun initGoodFoodRecycler() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragmentFoodMenuBrandBinding.recyclerview.layoutManager = layoutManager
        fragmentFoodMenuBrandBinding.recyclerview.adapter = goodFoodAdapter


        goodFoodAdapter.listener = object : ActivityAdapterListener {
            override fun buy(position: Int, obj: Foods.Food) {
                Log.i("TAG",obj.id.toString())
                cart.increaseFoodQuantity(obj.id.toString())
            }

            override fun removeItem(position: Int, obj: Foods.Food) {
                cart.removeFoodFromCart(obj.id.toString())
                Log.i("TAG","rm")
            }

            override fun increaseQuantity(position: Int, obj: Foods.Food, newValue: Int) {
                Log.i("TAG",obj.id.toString())
                cart.increaseFoodQuantity(obj.id.toString())
                Log.i("TAG","in")
            }

            override fun reduceQuantity(position: Int, obj: Foods.Food, newValue: Int) {
                Log.i("TAG","de")
                cart.reduceFoodQuantity(obj.id.toString())
            }
        }

    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is BrandState.GetBrandFood -> {
                    viewModel.setMainLoadingState(false)
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {
                        it.data?.foods?.forEach { foods->
                            goodFoodAdapter.addAll(foods.food!!)
                        }
                    }
                }
            }
            viewModel.stateOff()
        }
        cart.dataState.observe(this){dataState ->
            when(dataState){
                is CartOrderState.IncreaseFoodQuantity ->{
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {

                    }
                }
            }
            cart.stateOff()
        }
//    private fun setupRecyclerView(){
//        val data: ArrayList<GoodFoodModel> = ArrayList()
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
//        fragmentFoodMenuBrandBinding.recyclerview.adapter = GoodFoodAdapter(data)
//    }
    }

    private fun subscribeObserverTwo() {

        viewModel.dataState2.observe(this) { dataState2 ->
            when (dataState2) {
                is GoodFoodMenuState.GetUserCart -> {
                    viewModel.setMainLoadingState(false)
                    dataState2.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState2.response?.let {
                        goodFoodAdapter.addAllTwo(it.data?.orderItems!!)
                    }
                }
                is GoodFoodMenuState.IncreaseFoodQuantity -> {
                    dataState2.errorMessage?.let {
                        ToastUtil.showToast(it)
                        goodFoodAdapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.obj?.quantity!!
                        )
                    }
                    dataState2.response?.let {
                        goodFoodAdapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.newValue!!
                        )
                    }
                }
                is GoodFoodMenuState.ReduceFoodQuantity -> {
                    dataState2.errorMessage?.let {
                        ToastUtil.showToast(it)
                        goodFoodAdapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.obj?.quantity!!
                        )
                    }
                    dataState2.response?.let {
                        goodFoodAdapter.changeItemQuantity(
                            itemChangedQuantity?.position!!,
                            itemChangedQuantity?.newValue!!
                        )
                    }
                }
            }
        }
    }

    data class ItemChangedQuantityFood(
        val position: Int,
        val obj: OrderModel,
        val newValue: Int,
        var quantity: Int
    ) {

    }
}

//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.texonapp.foodtruck.R
//import com.texonapp.foodtruck.adapter.GoodFoodAdapter
//import com.texonapp.foodtruck.data.model.OrderModel
//import com.texonapp.foodtruck.data.model.RestaurantModel
//import com.texonapp.foodtruck.databinding.FragmentFoodMenuBrandBinding
//import com.texonapp.foodtruck.mvi.BrandState
//import com.texonapp.foodtruck.mvi.state.GoodFoodMenuState
//import com.texonapp.foodtruck.util.ToastUtil
//import com.texonapp.foodtruck.util.publicTools.AdapterListener
//import com.texonapp.foodtruck.view.base.BaseFragment
//import com.texonapp.foodtruck.viewmodel.BrandViewModel
//
//class FoodMenuBrandFragment : BaseFragment() {
//
//    lateinit var fragmentFoodMenuBrandBinding: FragmentFoodMenuBrandBinding
//    private val viewModel: BrandViewModel by viewModels()
//
//    private val goodFoodAdapter by lazy {
//        GoodFoodAdapter()
//    }
//    private var itemChangedQuantity: ItemChangedQuantityFood? = null
//
//    override fun listeners() {
//        initGoodFoodRecycler()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        subscribeObserver()
//        subscribeObserverTwo()
//        viewModel.getBrandFood(1)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        fragmentFoodMenuBrandBinding =
//            FragmentFoodMenuBrandBinding.inflate(layoutInflater, container, false)
//        // setupRecyclerView()
//
//        fragmentFoodMenuBrandBinding.included.imgBack.setOnClickListener {
//            findNavController().popBackStack()
//        }
//
//        fragmentFoodMenuBrandBinding.shoppingCart.setOnClickListener {
//            findNavController().navigate(R.id.action_foodMenuBrandFragment_to_cartFragment)
//        }
//        return fragmentFoodMenuBrandBinding.root
//    }
//
//    private fun initGoodFoodRecycler() {
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        fragmentFoodMenuBrandBinding.recyclerview.layoutManager = layoutManager
//        fragmentFoodMenuBrandBinding.recyclerview.adapter = goodFoodAdapter
//
//
//        goodFoodAdapter.listener = object : AdapterListener,
//            GoodFoodAdapter.ActivityAdapterListener {
//            override fun onItemClick(position: Int, obj: Any) {
//                if (obj is RestaurantModel) {
//
//                }
//            }
//
//
////            override fun increaseQuantity(position: Int, obj: OrderModel, newValue: Int) {
////                itemChangedQuantity = ItemChangedQuantityFood(position, obj, newValue)
////                goodFoodViewModel.increaseFoodQuantity(obj.id!!.toString())
////            }
//
////            override fun reduceQuantity(position: Int, obj: OrderModel, newValue: Int) {
////                itemChangedQuantity = ItemChangedQuantityFood(position, obj, newValue)
////                goodFoodViewModel.reduceFoodQuantity(obj.id!!.toString())
////            }
////
////            override fun removeItem(position: Int, obj: OrderModel) {
////                super.removeItem(position, obj)
////            }
//        }
//
//    }
//
//    private fun subscribeObserver() {
//        viewModel.dataState.observe(this) { dataState ->
//            when (dataState) {
//                is BrandState.GetBrandFood -> {
//                    viewModel.setMainLoadingState(false)
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//                    dataState.response?.let {
//                        goodFoodAdapter.addAll(it.dataFood!!.foods!!)
//                    }
//                }
//            }
//            viewModel.stateOff()
//        }
////    private fun setupRecyclerView(){
////        val data: ArrayList<GoodFoodModel> = ArrayList()
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        data.add(GoodFoodModel(R.drawable.good_food_menu_image, "Spacy Fresh Crap","Waronek kita","$ 35","Buy"))
////        fragmentFoodMenuBrandBinding.recyclerview.adapter = GoodFoodAdapter(data)
////    }
//    }
//
//    private fun subscribeObserverTwo() {
//
//        viewModel.dataState2.observe(this) { dataState2 ->
//            when (dataState2) {
//                is GoodFoodMenuState.GetUserCart -> {
//                    viewModel.setMainLoadingState(false)
//                    dataState2.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//                    dataState2.response?.let {
//                        goodFoodAdapter.addAllTwo(it.data?.orderItems!!)
//                    }
//                }
//                is GoodFoodMenuState.IncreaseFoodQuantity -> {
//                    dataState2.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                        goodFoodAdapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.obj?.quantity!!
//                        )
//                    }
//                    dataState2.response?.let {
//                        goodFoodAdapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.newValue!!
//                        )
//                    }
//                }
//                is GoodFoodMenuState.ReduceFoodQuantity -> {
//                    dataState2.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                        goodFoodAdapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.obj?.quantity!!
//                        )
//                    }
//                    dataState2.response?.let {
//                        goodFoodAdapter.changeItemQuantity(
//                            itemChangedQuantity?.position!!,
//                            itemChangedQuantity?.newValue!!
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//    data class ItemChangedQuantityFood(
//        val position: Int,
//        val obj: OrderModel,
//        val newValue: Int,
//        var quantity: Int
//    ) {
//
//    }
//}
