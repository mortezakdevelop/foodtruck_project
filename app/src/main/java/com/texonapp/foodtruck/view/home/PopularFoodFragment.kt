package com.texonapp.foodtruck.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.PopularFoodAdapter
import com.texonapp.foodtruck.databinding.FragmentPopularFoodBinding
import com.texonapp.foodtruck.model.PopularFoodModel
import com.texonapp.foodtruck.model.RestaurantBrandModel


class PopularFoodFragment : Fragment() {

    private lateinit var fragmentPopularFoodFragment: FragmentPopularFoodBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentPopularFoodFragment = FragmentPopularFoodBinding.inflate(layoutInflater,container,false)
        setupPopularFoodRecyclerView()

        fragmentPopularFoodFragment.included.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return fragmentPopularFoodFragment.root
    }

    private fun setupPopularFoodRecyclerView(){
        val data: ArrayList<PopularFoodModel> = ArrayList()
        data.add(PopularFoodModel(R.drawable.popular_food_item_image,"Spacy fresh crap","Waroenk kita","$ 35"))
        data.add(PopularFoodModel(R.drawable.popular_food_item_image,"Spacy fresh crap","Waroenk kita","$ 35"))
        data.add(PopularFoodModel(R.drawable.popular_food_item_image,"Spacy fresh crap","Waroenk kita","$ 35"))
        data.add(PopularFoodModel(R.drawable.popular_food_item_image,"Spacy fresh crap","Waroenk kita","$ 35"))
        data.add(PopularFoodModel(R.drawable.popular_food_item_image,"Spacy fresh crap","Waroenk kita","$ 35"))
        data.add(PopularFoodModel(R.drawable.popular_food_item_image,"Spacy fresh crap","Waroenk kita","$ 35"))

        fragmentPopularFoodFragment.rvPopularFood.adapter = PopularFoodAdapter(data)
    }
}