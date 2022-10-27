package com.texonapp.foodtruck.view.entry

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentSplashScreenBinding
import com.texonapp.foodtruck.util.IS_SEEN_INTRO
import com.texonapp.foodtruck.util.SharedPreferenceUtil
import com.texonapp.foodtruck.util.USER_TOKEN
import com.texonapp.foodtruck.view.base.BaseFragment

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        startCountDown()
        return view
    }

    override fun listeners() {

    }

    private fun startApp() {
      //  when {
//            !sharedPreferenceUtil!!.getBooleanValue(
//                IS_SEEN_INTRO
//            ) -> {
                findNavController().navigate(R.id.action_splashScreenFragment_to_mainActivity)
//            }
//            !existUser() -> {
//                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
//            }
//            existUser() -> {
//               // findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
//                requireActivity().finish()
//            }
//        }
    }

    private fun existUser(): Boolean {
        if (sharedPreferenceUtil.getStringValue(USER_TOKEN)
                .equals("")
        )
            return false
        return true
    }

    private fun startCountDown() {
        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                startApp()
            }
        }.start()
    }
}