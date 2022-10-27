package com.texonapp.foodtruck.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.MultyBrandAdapter
import com.texonapp.foodtruck.adapter.NotificationAdapter
import com.texonapp.foodtruck.databinding.FragmentNotificationBinding
import com.texonapp.foodtruck.model.NotificationModel

class NotificationFragment : Fragment() {

    lateinit var fragmentNotificationBinding: FragmentNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentNotificationBinding = FragmentNotificationBinding.inflate(layoutInflater,container,false)
        setupNotificationRecyclerview()

        fragmentNotificationBinding.included.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return fragmentNotificationBinding.root
    }

    private fun setupNotificationRecyclerview(){
        val data: ArrayList<NotificationModel> = ArrayList()
        data.add(NotificationModel(R.drawable.ic_dollar_image,"Topup for RM 100  was successful","10:00 Am"))
        data.add(NotificationModel(R.drawable.ic_x_button,"Your order has been canceled","22 Juny 2021"))

        fragmentNotificationBinding.rvNotification.layoutManager =
            LinearLayoutManager(requireContext())
        fragmentNotificationBinding.rvNotification.adapter = NotificationAdapter(data)
    }

}