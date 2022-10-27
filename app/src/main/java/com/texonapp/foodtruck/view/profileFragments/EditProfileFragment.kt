package com.texonapp.foodtruck.view.profileFragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentEditProfileBinding
import com.texonapp.foodtruck.mvi.state.ProfileState
import com.texonapp.foodtruck.util.BRAND_ID
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.ProfileViewModel
import java.io.ByteArrayOutputStream


class EditProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var reffralCode :String
    private lateinit var bundle: Bundle
    var picturePath: String? = null
    var fullName: String? = null
    var email:String? = null
    var birthDay:String? = null
    var password:String? = null
    var ImgFromStore: String? = null
    private var selectedImage:Uri? = null

    private val viewModel: ProfileViewModel by viewModels()



    var GetContent = registerForActivityResult(
        StartActivityForResult()
    ) { result ->
        // Handle the returned Uri
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data = result.data!!
            selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = requireActivity().contentResolver.query(
                selectedImage!!,
                filePathColumn,
                null,
                null,
                null
            )
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            picturePath = cursor.getString(columnIndex)
            cursor.close()
            binding.userImg.setImageBitmap(
                BitmapFactory.decodeFile(
                    picturePath
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments?:Bundle()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        reffralCode = bundle.getString(BRAND_ID,"0")
        binding.tvReffrallCode.text = reffralCode
        if(reffralCode.toInt() == 0){
            binding.tvReffrallCode.visibility = View.GONE
            binding.tvReffrallCodeName.visibility = View.GONE
        }

        ReadDataStore()
        setdefaultValue()
        setupSaveBtn()

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getphotofromGallery()
    }

    private fun subscribeObserver() {
        println("---------------------------------------------------- hello")
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
//                is ProfileState.GetProfile -> {
//                    viewModel.setMainLoadingState(false)
//                    dataState.errorMessage?.let {
//                        Log.i("TAG","ERROR")
//                        ToastUtil.showToast(it)
//                    }
//                    dataState.response?.let {
//
//                        ToastUtil.showToast("this is ok")
//                        println("---------------------------------------------- this is ok")
//                    }
//                }
                is ProfileState.PostProfile -> {
                    dataState.errorMessage?.let {
                        ToastUtil.showToast(it)

                    }
                    dataState.response?.let {
                        println(" response ok ------------------------------------------------ $it")

                        ToastUtil.showToast("Data Saved successful")
                    }
                }
            }
            viewModel.stateOff()
        }
    }

    private fun getphotofromGallery() {
        binding.rlImageProfile.setOnClickListener(View.OnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    2000
                )
            } else {
                val cameraIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                GetContent.launch(cameraIntent)
            }
        })
    }

    private fun setupSaveBtn() {
        binding.btnRegister.setOnClickListener(View.OnClickListener {
            writetoDataStore()
             Snackbar
                .make(binding.llProfile, "data saved successfully", Snackbar.LENGTH_LONG)
            .show()
        })
    }

    // Write BookMarks ArrayList From Shared Prefrence
    private fun writetoDataStore() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val editor = sharedPrefs.edit()
        if (picturePath != null) {
            editor.putString(
                "profileImg",
                encodeTobase64(BitmapFactory.decodeFile(picturePath))
            )
        }
        editor.putString(
            "fullName",
            binding.fullName.text.toString()
        )
        editor.putString(
            "email",
            binding.email.text.toString()
        )
        editor.putString(
            "password",
            binding.password.text.toString()
        )
        editor.apply()
    }

    private fun ReadDataStore() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        ImgFromStore = sharedPrefs.getString("profileImg", null)
        fullName = sharedPrefs.getString("fullName", "")
        email = sharedPrefs.getString("email", "")
        birthDay = sharedPrefs.getString("birthDay", "")
        password = sharedPrefs.getString("password","")
    }

    // decode string to bitmap
    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    // method for bitmap to base64
    fun encodeTobase64(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }


    private fun setdefaultValue() {
        if (ImgFromStore == null) {
            binding.userImg.setImageResource(R.drawable.profile_placeholder)
        } else {
            binding.userImg.setImageBitmap(
                decodeBase64(
                    ImgFromStore
                )
            )
        }
        binding.fullName.setText(fullName)
        binding.email.setText(email)
        binding.password.setText(password)
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        val fullName = binding.fullName.text
        binding.btnRegister.setOnClickListener {
            if (binding.fullName.length()!=0 && binding.email.length()!=0 && binding.password.length()!=0){
                ToastUtil.showToast("$fullName Registered Successfully")
            }else{
                ToastUtil.showToast("Fill in all the fields")
            }
        }
        binding.btnRegister.setOnClickListener {
            writetoDataStore()
            Snackbar
                .make(binding.llProfile, "data saved successfully", Snackbar.LENGTH_LONG)
                .show()
            subscribeObserver()
        }
    }
}