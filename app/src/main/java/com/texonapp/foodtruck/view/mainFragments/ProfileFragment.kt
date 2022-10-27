package com.texonapp.foodtruck.view.mainFragments

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.texonapp.foodtruck.MainActivity
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentProfileBinding
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.util.setImage
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.UserViewModel
import okio.ByteString.Companion.decodeBase64


class ProfileFragment : BaseFragment() {
    private lateinit var binding: FragmentProfileBinding
    private var ImgFromStore: String? = null
    private val viewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        hideKeyboard(requireActivity())
        setImageInProfile()
        val view = binding.root

        binding.edit.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.orders.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_ordersFragment)
        }

        binding.deliveryAddress.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_userAddressesFragment)
        }

        binding.about.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_aboutFragment)

        }
        binding.paymentMethods.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_paymentMethodsFragment)
        }
        binding.help.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_helpFragment)
        }

        binding.myDetails.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.signUpScan.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_profileRefferalScannerFragment)
        }
        return view
    }

    override fun listeners() {
        binding.scannerProfile.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_scannerProfileFragment)
        }

        binding.btnGmail.setOnClickListener {
            deleteToken()
        }
    }

    private fun setImageInProfile(){

        //get Data SharedPrefrence
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())

        ImgFromStore = sharedPrefs.getString("profileImg", null)

        //set profile image
        if (ImgFromStore == null) {
            binding.userImg.setImageResource(R.drawable.profile_placeholder)
        } else {
            binding.userImg.setImageBitmap(
                decodeBase64(
                    ImgFromStore
                )
            )
        }
    }

    // decode string to bitmap
    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }
        // close soft keyboard
        private fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    private fun deleteToken(){
            viewModel.deleteUserToken()
        //findNavController().navigate(R.id.action_profileFragment_to_loginFragment2)
            ToastUtil.showToast("Logout successfully")
        }
    }
