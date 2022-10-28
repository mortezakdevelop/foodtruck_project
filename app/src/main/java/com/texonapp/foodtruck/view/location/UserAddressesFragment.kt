package com.texonapp.foodtruck.view.location

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.adapter.UserAddressesAdapter
import com.texonapp.foodtruck.adapter.cardListener.LocationAddressClickListener
import com.texonapp.foodtruck.databinding.FragmentUserAddressesBinding
import com.texonapp.foodtruck.model.UserAddressModel
import com.texonapp.foodtruck.roomDb.entity.UserAddressDialogEntity
import com.texonapp.foodtruck.util.ToastUtil
import com.texonapp.foodtruck.view.base.BaseFragment
import com.texonapp.foodtruck.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserAddressesFragment : BaseFragment(),LocationAddressClickListener {

    private lateinit var binding: FragmentUserAddressesBinding
    private val viewModel: LocationViewModel by viewModels()
    lateinit var userAddressDialogEntity: UserAddressDialogEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_user_addresses,container,false)
        initRecyclerView()
        return binding.root
    }

    override fun listeners() {
        initMainToolbar(binding.toolbar)
        //initialData()
        binding.newAddress.setOnClickListener {

            addButtonNewAddress()
            // findNavController().navigate(R.id.action_userAddressesFragment_to_mapsFragment)
        }
 }

    private fun addButtonNewAddress(){
        val builder = AlertDialog.Builder(requireContext())
        val inflater: LayoutInflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.user_address_fragment_alert_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.et_address)

        if (arguments != null) {
            userAddressDialogEntity = arguments?.getParcelable("datamodel")!!
            editText.setText(userAddressDialogEntity.userAddressModel.address)
        }

        with(builder) {
            setTitle("New Address")
            setPositiveButton("Save") { dialog, which ->


                if (editText.text.isNullOrBlank()) {
                    Snackbar.make(
                        binding.llAddress,
                        "please enter address ...",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    val title = editText.text.toString()
                    val userAddressModel = UserAddressModel(title)
                    viewModel.insertToNoteModel(userAddressModel)
                    ToastUtil.showToast("The address has been successfully added to the list")

                }
            }

            setView(dialogLayout)
            show()
        }
    }


    override fun onItemRVClickListener(userAddressDialogEntity: UserAddressDialogEntity) {
        super.onItemRVClickListener(userAddressDialogEntity)

        val builder = AlertDialog.Builder(requireContext())
        val inflater: LayoutInflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.user_address_fragment_alert_dialog, null)
        val editText = dialogLayout.findViewById<EditText>(R.id.et_address)

        if (arguments != null) {
            this.userAddressDialogEntity = arguments?.getParcelable("datamodel")!!
            editText.setText(userAddressDialogEntity.userAddressModel.address)
        }

        with(builder) {
            setTitle("Update Address")
            setPositiveButton("Save") { dialog, which ->


                if (editText.text.isNullOrBlank()) {
                    Snackbar.make(
                        binding.llAddress,
                        "please enter address ...",
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    userAddressDialogEntity.userAddressModel.address = editText.text.toString()
                    viewModel.updateModel(userAddressDialogEntity)
                    ToastUtil.showToast("address updated")
                }
            }

            setView(dialogLayout)
            show()
        }

    }

    private fun initRecyclerView() {
        viewModel.liveData.observe(viewLifecycleOwner){ listData ->
            val data: ArrayList<UserAddressDialogEntity> = ArrayList()
            listData.forEach {
                if (!data.contains(it)) {
                    data.add(it)
                }
            }
            binding.recyclerView.adapter = UserAddressesAdapter(data,this)
        }

//        val data: ArrayList<UserAddressModel> = ArrayList()
//        data.add(UserAddressModel("title1"))
//        data.add(UserAddressModel("title2"))
//        data.add(UserAddressModel("title 3"))
//        data.add(UserAddressModel("title 4"))
//        data.add(UserAddressModel("title 5"))
//        data.add(UserAddressModel("title 6"))
//        data.add(UserAddressModel("title 7"))
//
//        binding.recyclerView.adapter = UserAddressesAdapter(data)
    }



//    private fun initialData() {
//        initRecycler()
//        if (adapter.itemCount == 0)
//            adapter.addAll(getUserAddresses())
//    }
//
//    private fun initRecycler() {
//        val layoutManager = LinearLayoutManager(context)
//        binding.recyclerView.layoutManager = layoutManager
//        binding.recyclerView.adapter = adapter
//
//        adapter.listener = object : AdapterListener {
//            override fun onItemClick(position: Int, obj: Any) {
//
//            }
//        }
//    }
//
//    private fun getUserAddresses(): ArrayList<UserAddressModel> {
//        val data = ArrayList<UserAddressModel>()
//        data.add(
//            UserAddressModel(
//                "Harlingen, 3604 Jerome Avenue, No 78550",
//                true
//            )
//        )
//        data.add(
//            UserAddressModel(
//                "Harlingen, 3604 Jerome Avenue, No 78550"
//            )
//        )
//        data.add(
//            UserAddressModel(
//                "Harlingen, 3604 Jerome Avenue, No 78550"
//            )
//        )
//        return data
//    }
}
