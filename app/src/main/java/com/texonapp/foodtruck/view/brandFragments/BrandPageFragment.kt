package com.texonapp.foodtruck.view.brandFragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.BrandPopularFoodAdapter
import com.texonapp.foodtruck.adapter.TestimotionAdapter
import com.texonapp.foodtruck.data.model.RestaurantModel
import com.texonapp.foodtruck.databinding.FragmentBrandPageBinding
import com.texonapp.foodtruck.model.TestimonialModel
import com.texonapp.foodtruck.mvi.BrandState
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.BrandViewModel

class BrandPageFragment : BaseFragment() {
    private lateinit var id :String
    private lateinit var bundle: Bundle
    lateinit var sp:SharedPreferences

    lateinit var fragmentBrandPageBinding: FragmentBrandPageBinding
    private val viewModel: BrandViewModel by viewModels()
    var order = arrayOf<String?>(
        "Delivery", "Dine in",
        "Take away"
    )

    private val brandPopularFoodAdapter by lazy {
        BrandPopularFoodAdapter()
    }

//    private val testimotionAdapter by lazy {
//        TestimotionAdapter()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObserver()
        bundle = arguments?:Bundle()
        viewModel.getBrandDetail(1)
    }

    override fun listeners() {
        initBrandPopularFoodAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentBrandPageBinding =
            FragmentBrandPageBinding.inflate(layoutInflater, container, false)

        id = bundle.getString(BRAND_ID,"0")
        Log.i("TAG","BRAND ID D:$id")
        viewModel.getBrandDetail(id.toInt())

        fragmentBrandPageBinding.lifecycleOwner = this
        fragmentBrandPageBinding.viewModel = viewModel

        requireActivity().window.decorView.systemUiVisibility
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor
//        setupRecyclerview()
        setupTestimonialsRecyclerview()

        //collapsing bottom sheet setup
        val sheetBehavior: BottomSheetBehavior<NestedScrollView> =
            BottomSheetBehavior.from(fragmentBrandPageBinding.included.nsvBottomSheet)
        sheetBehavior.isHideable = false
        sheetBehavior.isFitToContents = true

        fragmentBrandPageBinding.included.tvViewAll.setOnClickListener {
            val bandel = Bundle()
            bandel.putString(BRAND_ID,id)
            Log.i("TAG","BRAND ID: $id")
            findNavController().navigate(R.id.action_brandPageFragment_to_foodMenuBrandFragment,bandel)
        }

        return fragmentBrandPageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,order) as SpinnerAdapter
        fragmentBrandPageBinding.included.spinner.adapter = arrayAdapter
        fragmentBrandPageBinding.included.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                //save to sharedPreferences
                sp = requireContext().getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE)
                val prefEditor: SharedPreferences.Editor = sp.edit()
                prefEditor.putString("typeFood",fragmentBrandPageBinding.included.spinner.selectedItem.toString())
                prefEditor.commit()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
//    private fun setupRecyclerview(){
//        val data: ArrayList<BrandPopularFoodModel> = ArrayList()
//        data.add(BrandPopularFoodModel(R.drawable.pizza_brand_item_image_one, "Spacy Fresh Crap","12$"))
//        data.add(BrandPopularFoodModel(R.drawable.pizza_brand_item_image_one, "Spacy Fresh Crap","16$"))
//        data.add(BrandPopularFoodModel(R.drawable.pizza_brand_item_image_one, "Spacy Fresh Crap","12$"))
//        fragmentBrandPageBinding.included.recyclerview.adapter = BrandPopularFoodAdapter(data)
//    }

    private fun setupTestimonialsRecyclerview() {
        val data: ArrayList<TestimonialModel> = ArrayList()
        data.add(
            TestimonialModel(
                R.drawable.good_food_menu_image,
                "Dianne Russel",
                "12 April 2021",
                R.drawable.star_image_testimotion,
                "This Is great, So delicious! You Must Here, With Your family You Must Here, With Your family..."
            )
        )
        data.add(
            TestimonialModel(
                R.drawable.good_food_menu_image,
                "Dianne Russel",
                "12 April 2021",
                R.drawable.star_image_testimotion,
                "This Is great, So delicious! You Must Here, With Your family You Must Here, With Your family..."
            )
        )
        fragmentBrandPageBinding.included.rvTestimonials.adapter = TestimotionAdapter(data)
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is BrandState.GetBrandDetail -> {
                    viewModel.setMainLoadingState(false)
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {
                        id = it.brandDetail?.id.toString()
                        fragmentBrandPageBinding.included.tvTitle.text = it.brandDetail?.name
                        fragmentBrandPageBinding.included.tvSubTitle.text = it.brandDetail?.address
                        fragmentBrandPageBinding.included.tvTextBottomSheet.text =
                            it.brandDetail!!.description
                        brandPopularFoodAdapter.addAll(it.brandDetail.foods!!)
                    }
                }
            }
            viewModel.stateOff()
        }
    }

    private fun initBrandPopularFoodAdapter() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        fragmentBrandPageBinding.included.recyclerview.layoutManager = layoutManager
        fragmentBrandPageBinding.included.recyclerview.adapter = brandPopularFoodAdapter

        brandPopularFoodAdapter.listener = object : AdapterListener {
            override fun onItemClick(position: Int, obj: Any) {
                if (obj is RestaurantModel) {

                }
            }
        }
    }
}

//
//import android.os.Bundle
//import android.view.*
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.SpinnerAdapter
//import androidx.core.widget.NestedScrollView
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.android.material.bottomsheet.BottomSheetBehavior
//import com.texonapp.foodtruck.R
//import com.texonapp.foodtruck.adapter.BrandPopularFoodAdapter
//import com.texonapp.foodtruck.adapter.TestimotionAdapter
//import com.texonapp.foodtruck.data.model.RestaurantModel
//import com.texonapp.foodtruck.databinding.FragmentBrandPageBinding
//import com.texonapp.foodtruck.model.TestimonialModel
//import com.texonapp.foodtruck.mvi.BrandState
//import com.texonapp.foodtruck.util.ToastUtil
//import com.texonapp.foodtruck.util.publicTools.AdapterListener
//import com.texonapp.foodtruck.view.base.BaseFragment
//import com.texonapp.foodtruck.viewmodel.BrandViewModel
//import kotlinx.android.synthetic.main.fragment_brand_page.*
//
//
//class BrandPageFragment : BaseFragment() {
//
//    lateinit var fragmentBrandPageBinding: FragmentBrandPageBinding
//    private val viewModel: BrandViewModel by viewModels()
//    var order = arrayOf<String?>(
//        "Delivery", "Dine in",
//        "Take away"
//    )
//    private val brandPopularFoodAdapter by lazy {
//        BrandPopularFoodAdapter()
//    }
//
////    private val testimotionAdapter by lazy {
////        TestimotionAdapter()
////    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        subscribeObserver()
//        viewModel.getBrandDetail(1)
//    }
//
//    override fun listeners() {
//        initBrandPopularFoodAdapter()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        fragmentBrandPageBinding =
//            FragmentBrandPageBinding.inflate(layoutInflater, container, false)
//
//        fragmentBrandPageBinding.lifecycleOwner = this
//        fragmentBrandPageBinding.viewModel = viewModel
//
//        requireActivity().window.decorView.systemUiVisibility
//        val window: Window = requireActivity().window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor
////        setupRecyclerview()
//        setupTestimonialsRecyclerview()
//
//        //collapsing bottom sheet setup
//        val sheetBehavior: BottomSheetBehavior<NestedScrollView> =
//            BottomSheetBehavior.from(fragmentBrandPageBinding.included.nsvBottomSheet)
//        sheetBehavior.isHideable = false
//        sheetBehavior.isFitToContents = true
//
//        fragmentBrandPageBinding.included.tvViewAll.setOnClickListener {
//            findNavController().navigate(R.id.action_brandPageFragment_to_foodMenuBrandFragment)
//        }
//
//        return fragmentBrandPageBinding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,order) as SpinnerAdapter
//        fragmentBrandPageBinding.included.spinner.adapter = arrayAdapter
//        fragmentBrandPageBinding.included.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//            }
//        }
//    }
//
////    private fun setupRecyclerview(){
////        val data: ArrayList<BrandPopularFoodModel> = ArrayList()
////        data.add(BrandPopularFoodModel(R.drawable.pizza_brand_item_image_one, "Spacy Fresh Crap","12$"))
////        data.add(BrandPopularFoodModel(R.drawable.pizza_brand_item_image_one, "Spacy Fresh Crap","16$"))
////        data.add(BrandPopularFoodModel(R.drawable.pizza_brand_item_image_one, "Spacy Fresh Crap","12$"))
////        fragmentBrandPageBinding.included.recyclerview.adapter = BrandPopularFoodAdapter(data)
////    }
//
//    private fun setupTestimonialsRecyclerview() {
//        val data: ArrayList<TestimonialModel> = ArrayList()
//        data.add(
//            TestimonialModel(
//                R.drawable.profile_testimotional,
//                "Dianne Russel",
//                "12 April 2021",
//                R.drawable.star_image_testimotion,
//                "This Is great, So delicious! You Must Here, With Your family You Must Here, With Your family..."
//            )
//        )
//        data.add(
//            TestimonialModel(
//                R.drawable.profile_testimotional,
//                "Dianne Russel",
//                "12 April 2021",
//                R.drawable.star_image_testimotion,
//                "This Is great, So delicious! You Must Here, With Your family You Must Here, With Your family..."
//            )
//        )
//        fragmentBrandPageBinding.included.rvTestimonials.adapter = TestimotionAdapter(data)
//    }
//
//    private fun subscribeObserver() {
//        viewModel.dataState.observe(this) { dataState ->
//            when (dataState) {
//                is BrandState.GetBrandDetail -> {
//                    viewModel.setMainLoadingState(false)
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//                    dataState.response?.let {
//                        fragmentBrandPageBinding.included.tvTitle.text = it.brandDetail?.name
//                        fragmentBrandPageBinding.included.tvSubTitle.text = it.brandDetail?.address
//                        fragmentBrandPageBinding.included.tvTextBottomSheet.text =
//                            it.brandDetail!!.description
//                        brandPopularFoodAdapter.addAll(it.brandDetail.foods!!)
//                    }
//                }
//            }
//            viewModel.stateOff()
//        }
//    }
//
//    private fun initBrandPopularFoodAdapter() {
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        fragmentBrandPageBinding.included.recyclerview.layoutManager = layoutManager
//        fragmentBrandPageBinding.included.recyclerview.adapter = brandPopularFoodAdapter
//
//        brandPopularFoodAdapter.listener = object : AdapterListener {
//            override fun onItemClick(position: Int, obj: Any) {
//                if (obj is RestaurantModel) {
//
//                }
//            }
//        }
//    }
//}