package com.texonapp.foodtruck.view.location

//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.graphics.Color
//import android.location.LocationManager
//import android.os.Bundle
//import android.os.Looper
//import android.provider.Settings
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.app.ActivityCompat
//import androidx.fragment.app.viewModels
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.android.material.bottomsheet.BottomSheetDialog
//import com.mapbox.android.core.location.*
//import com.mapbox.android.core.permissions.PermissionsListener
//import com.mapbox.android.core.permissions.PermissionsManager
//import com.mapbox.mapboxsdk.Mapbox
//import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
//import com.mapbox.mapboxsdk.geometry.LatLng
//import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
//import com.mapbox.mapboxsdk.location.LocationComponentOptions
//import com.mapbox.mapboxsdk.location.modes.CameraMode
//import com.mapbox.mapboxsdk.location.modes.RenderMode
//import com.mapbox.mapboxsdk.maps.MapboxMap
//import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
//import com.mapbox.mapboxsdk.maps.Style
//import com.texonapp.foodtruck.R
//import com.texonapp.foodtruck.adapter.SuggestAddressAdapter
//import com.texonapp.foodtruck.adapter.UserAddressesAdapter
//import com.texonapp.foodtruck.databinding.DialogSubmitAddressBinding
//import com.texonapp.foodtruck.databinding.FragmentMapsBinding
//import com.texonapp.foodtruck.mvi.LocationState
//import com.texonapp.foodtruck.util.ToastUtil
//import com.texonapp.foodtruck.util.publicTools.AdapterListener
//import com.texonapp.foodtruck.view.base.BaseFragment
//import com.texonapp.foodtruck.viewmodel.LocationViewModel

//const val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L
//const val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5
//
//class MapsFragment : BaseFragment(), OnMapReadyCallback {
//
//    private lateinit var binding: FragmentMapsBinding
//    private val viewModel: LocationViewModel by viewModels()
//    var map: MapboxMap? = null
//    private var mapStyle: Style? = null
//    private lateinit var callback: MyLocationCallback
//    private var locationEngine: LocationEngine? = null
//
//    private val adapter: SuggestAddressAdapter by lazy {
//        SuggestAddressAdapter()
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        subscribeObservers()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        Mapbox.getInstance(
//            requireContext(),
//            getString(R.string.mapbox_access_token)
//        )
//        binding = FragmentMapsBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//        return view
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        callback = MyLocationCallback()
//        binding.mapView.onCreate(savedInstanceState)
//        binding.mapView.getMapAsync(this)
//    }
//
//    override fun listeners() {
//        binding.userLocation.setOnClickListener {
//            enableLocationComponent()
//        }
//
//        binding.setLocation.setOnClickListener {
//            showProgress()
//            viewModel.getUserAddressFromLocation(
//                map?.cameraPosition?.target?.longitude!!,
//                map?.cameraPosition?.target?.latitude!!
//            )
//        }
//        searchListener()
//        initSuggestRecycler()
//    }
//
//    private fun subscribeObservers() {
//        viewModel.dataState.observe(this) { dataState ->
//            when (dataState) {
//                is LocationState.UserAddressByLocation -> {
//                    hideProgress()
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//
//                    dataState.response?.let {
//                        showSubmitAddressDialog(it)
//                    }
//                    viewModel.stateOff()
//                }
//                is LocationState.SuggestLocations -> {
//                    hideProgress()
//                    dataState.errorMessage?.let {
//                        ToastUtil.showToast(it)
//                    }
//
//                    dataState.response?.let {
//                        if (it.isEmpty()){
//                            ToastUtil.showToast(getString(R.string.nothing_found))
//                            binding.suggestRecycler.visibility = View.GONE
//                        }
//                        else{
//                            adapter.clearAll()
//                            adapter.addAll(it)
//                            binding.suggestRecycler.visibility = View.VISIBLE
//                        }
//                    }
//                    viewModel.stateOff()
//                }
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        hideBottomNavigation()
//        binding.mapView.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        binding.mapView.onPause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        showBottomNavigation()
//        binding.mapView.onStop()
//    }
//
//    private fun searchListener() {
//        binding.searchQuery.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable) {}
//            override fun beforeTextChanged(
//                s: CharSequence, start: Int,
//                count: Int, after: Int
//            ) {
//            }
//
//            override fun onTextChanged(
//                s: CharSequence, start: Int,
//                before: Int, count: Int
//            ) {
//                if (s.isEmpty()) {
//                    binding.clearSearch.visibility = View.GONE
//                    binding.suggestRecycler.visibility = View.GONE
//                } else {
//                    binding.clearSearch.visibility = View.VISIBLE
//                }
//            }
//        })
//        binding.clearSearch.setOnClickListener {
//            binding.suggestRecycler.visibility = View.GONE
//            binding.searchQuery.setText("")
//        }
//        binding.searchAddress.setOnClickListener {
//            if (binding.searchQuery.text.toString().isNotEmpty()){
//                showProgress()
//                viewModel.getSuggestLocations(binding.searchQuery.text.toString(), SearchOptions())
//            }
//            else {
//                ToastUtil.showToast(R.string.please_fill_this_field)
//            }
//        }
//    }
//
//    private fun initSuggestRecycler() {
//        val layoutManager = LinearLayoutManager(context)
//        binding.suggestRecycler.layoutManager = layoutManager
//        binding.suggestRecycler.adapter = adapter
//
//        adapter.listener = object : AdapterListener {
//            override fun onItemClick(position: Int, obj: Any) {
//                if (obj is SearchSuggestion){
//
//                }
//            }
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun showSubmitAddressDialog(address: SearchAddress) {
//        val dialogBinding = DialogSubmitAddressBinding.inflate(layoutInflater, null, false)
//        val dialog =
//            BottomSheetDialog(requireContext(), R.style.ThemeOverlay_App_BottomSheetDialog)
//        dialog.setContentView(dialogBinding.root)
//        dialogBinding.address.setText(address.street + ", " + address.region + ", " + address.district)
//        dialogBinding.submit.setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.show()
//    }
//
//    override fun onMapReady(mapboxMap: MapboxMap) {
//        map = mapboxMap
//        mapboxMap.setStyle(Style.OUTDOORS) {
//            binding.userLocation.visibility = View.VISIBLE
//            mapStyle = it
//            enableLocationComponent()
//        }
//    }
//
//    private fun statusCheck() {
//        // Check if permissions are enabled and if not request
//        val manager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            buildAlertMessageNoGps()
//        } else {
//            Log.d("gps status", "gps is enable.")
//        }
//
//    }
//
//    private fun enableLocationComponent() {
//        // Check if permissions are enabled and if not request
//        statusCheck()
//        if (PermissionsManager.areLocationPermissionsGranted(activity)) {
//            try {
//                // Create and customize the LocationComponent's options
//                val customLocationComponentOptions =
//                    LocationComponentOptions.builder(requireActivity())
//                        .elevation(5f)
//                        .accuracyAlpha(.6f)
//                        .accuracyColor(Color.RED)
//                        .build()
//                // Get an instance of the component
//                val locationComponent = map?.locationComponent
//                val locationComponentActivationOptions =
//                    LocationComponentActivationOptions.builder(requireActivity(), mapStyle!!)
//                        .locationComponentOptions(customLocationComponentOptions).build()
//                // Activate with options
//                locationComponent?.activateLocationComponent(locationComponentActivationOptions)
//                // Enable to make component visible
//                if (ActivityCompat.checkSelfPermission(
//                        requireContext(),
//                        Manifest.permission.ACCESS_FINE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                        requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
//                    ) != PackageManager.PERMISSION_GRANTED
//                ) {
//                    return
//                }
//                locationComponent?.isLocationComponentEnabled = true
//                // Set the component's camera mode
//                locationComponent?.cameraMode = CameraMode.TRACKING
//                // Set the component's render mode
//                locationComponent?.renderMode = RenderMode.COMPASS
//
//                initLocationEngine()
//
//                // Add the location icon click listener
//                locationComponent?.addOnLocationClickListener {}
//            } catch (e: NullPointerException) {
//                e.printStackTrace()
//            }
//        } else {
//            val permissionsManager = PermissionsManager(object : PermissionsListener {
//                override fun onExplanationNeeded(permissionsToExplain: List<String>) {}
//                override fun onPermissionResult(granted: Boolean) {
//                    if (granted) {
//                        enableLocationComponent()
//                    } else {
//                        ToastUtil.showToast(getString(R.string.permission_not_received))
//                    }
//                }
//            })
//            permissionsManager.requestLocationPermissions(activity)
//        }
//
//    }
//
//    private fun initLocationEngine() {
//        locationEngine = LocationEngineProvider.getBestLocationEngine(requireActivity())
//        val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
//            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
//            .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build()
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return
//        }
//        locationEngine?.requestLocationUpdates(request, callback, Looper.getMainLooper())
//        locationEngine?.getLastLocation(callback)
//    }
//
//    private fun animateCameraWithZoom(lat: Double, lng: Double) {
//        val samplePoint = LatLng(lat, lng)
//        val sampleZoom = 11
//        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(samplePoint, sampleZoom.toDouble()))
//    }
//
//    inner class MyLocationCallback : LocationEngineCallback<LocationEngineResult> {
//
//        override fun onSuccess(result: LocationEngineResult) {
//            result.lastLocation?.let {
//                map?.locationComponent?.forceLocationUpdate(result.lastLocation)
//            }
//        }
//
//        override fun onFailure(exception: java.lang.Exception) {}
//    }
//
//    private fun buildAlertMessageNoGps() {
//        val builder = AlertDialog.Builder(requireContext())
//        builder.setMessage(getString(R.string.your_location_service_is_disabled))
//            .setCancelable(false)
//            .setPositiveButton(
//                getString(R.string.yes)
//            ) { _, _ -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
//            .setNegativeButton(
//                getString(R.string.no)
//            ) { dialog, _ -> dialog.cancel() }
//        val alert = builder.create()
//        alert.show()
//    }
//
//}