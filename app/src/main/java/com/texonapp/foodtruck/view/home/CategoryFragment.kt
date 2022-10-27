package com.texonapp.foodtruck.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.CategoryAdapter
import com.texonapp.foodtruck.adapter.MultyBrandAdapter
import com.texonapp.foodtruck.databinding.FragmentCategoryBinding
import com.texonapp.foodtruck.model.CategoryModel
import com.texonapp.foodtruck.model.RestaurantBrandModel

class CategoryFragment : Fragment() {

    lateinit var fragmentCategoryBinding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentCategoryBinding = FragmentCategoryBinding.inflate(layoutInflater,container,false)
        setupCategoryAdapter()
        return fragmentCategoryBinding.root
    }

    private fun setupCategoryAdapter(){
        val data: ArrayList<CategoryModel> = ArrayList()
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#1A53B175","#8353B175"))
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#1AF8A44C","#83F8A44C"))
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#40F3FF6C","#F7A593"))
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#40D3B0E0","#D3B0E0"))
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#40FDE598","#FDE598"))
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#40B7DFF5","#B7DFF5"))
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#40836AF6","#40836AF6"))
        data.add(CategoryModel(R.drawable.italian_pizza_image, "Pizza","#40D73B77","#40D73B77"))

        fragmentCategoryBinding.recyclerviewCategory.layoutManager =
            GridLayoutManager(requireContext(),2)
        fragmentCategoryBinding.recyclerviewCategory.adapter = CategoryAdapter(data)
    }
}