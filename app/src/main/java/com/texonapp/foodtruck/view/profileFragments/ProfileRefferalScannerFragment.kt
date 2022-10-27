package com.texonapp.foodtruck.view.profileFragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentOrderBinding
import com.texonapp.foodtruck.databinding.FragmentProfileRefferalScannerBinding
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.ToastUtil

class ProfileRefferalScannerFragment : Fragment() {

    lateinit var binding: FragmentProfileRefferalScannerBinding
    private lateinit var codeScanner: CodeScanner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileRefferalScannerBinding.inflate(layoutInflater, container, false)

        if (ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.CAMERA), 123)
        }else{
            startScanning()
        }
        return binding.root
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
                val randomId = (100000..1000000).shuffled().last().toString()
                val bandel = Bundle()
                bandel.putString(BRAND_ID, randomId)

                findNavController().navigate(R.id.action_profileRefferalScannerFragment_to_editProfileFragment,bandel)
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