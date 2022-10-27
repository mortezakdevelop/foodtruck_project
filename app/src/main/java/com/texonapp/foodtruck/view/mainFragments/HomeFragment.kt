package com.texonapp.foodtruck.view.mainFragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.ActivitiesAdapter
import com.texonapp.foodtruck.adapter.RestaurantBrandAdapter
import com.texonapp.foodtruck.adapter.SliderImageAdapter
import com.texonapp.foodtruck.adapter.cardListener.HomeCardClickListener
import com.texonapp.foodtruck.data.model.InternalModel
import com.texonapp.foodtruck.data.model.RestaurantModel
import com.texonapp.foodtruck.databinding.FragmentHomeBinding
import com.texonapp.foodtruck.mvi.BrandState
import com.texonapp.foodtruck.mvi.HomeState
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.MainApplication.Companion.sharedPreferenceUtil
import com.texonapp.foodtruck.util.TOKEN
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.publicTools.AdapterListener
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.BrandViewModel
import com.texonapp.foodtruck.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val brandViewModel:BrandViewModel by viewModels()
    private val restaurantBrandAdapter by lazy {
        RestaurantBrandAdapter()
    }

    private val activitiesAdapter by lazy {
        ActivitiesAdapter(requireContext())
    }

    private val sliderAdapter by lazy {
        SliderImageAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferenceUtil?.saveValue(TOKEN, "Bearer 4|ZZYgCq90KBdO7s6aFvE9UyFd24sr8zbHwdR8eNf0")
        subscribeObserver()
        viewModel.getHomePage()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // when we are in home fragment and back pressed button , finish fragment

    }

    override fun listeners() {
        initRestaurantBrandRecycler()
        initActivitiesRecycler()
        initSlider()

        binding.notification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
        }

        binding.shoppingCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.tvBrandViewMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_multyBrandsFragment)
      }

        binding.tvActivitiesViewMore.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_popularFoodFragment)
        }
        binding.location.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapsFragmentForHome)
        }
    }

    private fun initRestaurantBrandRecycler() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.restaurantBrandRecycler.layoutManager = layoutManager
        binding.restaurantBrandRecycler.adapter = restaurantBrandAdapter

        restaurantBrandAdapter.homeListener = object : HomeCardClickListener, AdapterListener {
            override fun onItemHomeClickListener(restaurantModel: RestaurantModel) {
                super.onItemHomeClickListener(restaurantModel)
                val bundle = Bundle()
                bundle.putString(BRAND_ID,restaurantModel.id)
                findNavController().navigate(R.id.action_homeFragment_to_brandPageFragment,bundle)
            }
        }
    }

    private fun initActivitiesRecycler() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.activitiesRecycler.layoutManager = layoutManager
        binding.activitiesRecycler.adapter = activitiesAdapter

//        activitiesAdapter.listener = object : ActivitiesAdapter.ActivityAdapterListener {
//            override fun read(position: Int, obj: ActivityModel) {
//
//            }
//
//            override fun share(position: Int, obj: ActivityModel) {
//
//            }
//
//            override fun like(position: Int, obj: ActivityModel) {
//
//            }
//        }
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is HomeState.GetHomePage -> {
                    viewModel.setMainLoadingState(false)
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)
                    }
                    dataState.response?.let {
                        restaurantBrandAdapter.addAll(it.data?.restaurants!!)
                        activitiesAdapter.addAll(it.data.activities!!)
                        sliderAdapter.addAll(it.data.promotions!!.internal!!)
                        binding.dotsIndicator.setViewPager2(binding.viewPagerImageSlider)
                        binding.notification.badgeValue = it.data.user!!.notifications!!
                    }
                }
            }

            viewModel.stateOff()
        }
        brandViewModel.dataState.observe(this) { dataState ->
            when(dataState) {
                is BrandState.GetBrands ->{
                    dataState.errorMessage?.let{
                        ToastUtil.showToast(it)
                    }
                    dataState.responce?.let {

                    }
                }
            }
        }
    }

    private fun initSlider() {
        binding.viewPagerImageSlider.adapter = sliderAdapter
        binding.viewPagerImageSlider.offscreenPageLimit = 3
        sliderAdapter.listener = object : AdapterListener {
            override fun onItemClick(position: Int, obj: Any) {
                if (obj is InternalModel) {

                }
            }
        }
        binding.dotsIndicator.setViewPager2(binding.viewPagerImageSlider)
    }

    // close soft keyboard
    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}