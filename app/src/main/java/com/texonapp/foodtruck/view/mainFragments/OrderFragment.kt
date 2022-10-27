package com.texonapp.foodtruck.view.mainFragments

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.*
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentOrderBinding
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.ToastUtil
import java.util.jar.Manifest

class OrderFragment : Fragment() {

    lateinit var binding: FragmentOrderBinding
    private lateinit var codeScanner: CodeScanner
    private lateinit var id :String
    private lateinit var subString:CharSequence


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)

        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA), 123)
        }else{
            startScanning()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // initialise scanner
    private fun startScanning(){
        codeScanner = CodeScanner(requireContext(),binding.scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread{
                val bandel = Bundle()
                if(it.text.length == 21) {
                    subString = it.text.subSequence(17, 20)
                }else if (it.text.length == 20){
                    subString = it.text.subSequence(17,19)
                }
                bandel.putString(BRAND_ID, subString.toString())
                Log.i("TAG","BRAND ID: ${it.text}")
                println("this is scan code --------------------------------- ${it.text.length}")

                findNavController().navigate(R.id.action_orderFragment_to_foodMenuBrandFragment,bandel)
//                ToastUtil.showToast("result is : ${it.text}")
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            requireActivity().runOnUiThread{
                ToastUtil.showToast("error is : ${it.message}")
            }
        }

        binding.scannerView.setOnClickListener{
            codeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 123){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ToastUtil.showToast("Camera permission granded")
                startScanning()
            }else{

                ToastUtil.showToast("Camera Permission denied")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized){
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized){
            codeScanner.releaseResources()
        }
        super.onPause()
    }
}