package com.texonapp.foodtruck.view.profileFragments

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.*
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentScannerProfileBinding
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.ToastUtil
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class ScannerProfileFragment : Fragment() {

    lateinit var binding: FragmentScannerProfileBinding
    private lateinit var codeScanner: CodeScanner
    private lateinit var bitmapDrawable: BitmapDrawable
    lateinit var imgBitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScannerProfileBinding.inflate(layoutInflater, container, false)
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.CAMERA
            ) ==
            PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                123
            )
        } else {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bitmapDrawable = binding.ivScan.drawable as BitmapDrawable
        imgBitmap = bitmapDrawable.bitmap

        binding.btnScan.setOnClickListener {

            shareImage()

        }
    }

    private fun shareImage() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        val textToShare: String = "Scan Qr code"
        val imageUri: Uri? = saveImage(imgBitmap, requireContext())
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)
        intent.putExtra(Intent.EXTRA_SUBJECT, "new app")
        intent.putExtra(Intent.EXTRA_TEXT, textToShare)
        startActivity(Intent.createChooser(intent, "Share"))

    }

    private fun saveImage(image: Bitmap, context: Context): Uri? {
        var imageFolder = File(requireContext().cacheDir, "image")
        var uri: Uri? = null
        try {
            imageFolder.mkdirs()
            var file = File(imageFolder, "shared_image.jpg")
            var stream = FileOutputStream(file)
            image.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            stream.flush()
            stream.close()
            uri = FileProvider.getUriForFile(
                Objects.requireNonNull(context.applicationContext),
                "com.texonapp.foodtruck" + ".provider",
                file
            )

        } catch (e: IOException) {
            Log.d("TAG", "saveImage: " + e.message)
        }

        return uri
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ToastUtil.showToast("Camera permission granded")

            } else {

                ToastUtil.showToast("Camera Permission denied")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized) {
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized) {
            codeScanner.releaseResources()
        }
        super.onPause()
    }
}

