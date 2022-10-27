package com.texonapp.foodtruck.view.location

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.texonapp.foodtruck.R
import com.texonapp.foodtruck.databinding.FragmentMapsForHomeBinding
import com.texonapp.foodtruck.util.ToastUtil
import kotlinx.android.synthetic.main.fragment_home.*

class MapsFragmentForHome : Fragment() {
    lateinit var fragmentMapsForHomeBinding: FragmentMapsForHomeBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionCode = 101
    private lateinit var currentLocation:Location


    private val callback = OnMapReadyCallback { googleMap ->
       val latLng = LatLng(currentLocation.latitude,currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("You are here")
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,7f))
        googleMap.addMarker(markerOptions)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getCurrentLocation()

        fragmentMapsForHomeBinding = FragmentMapsForHomeBinding.inflate(layoutInflater,container,false)
        return fragmentMapsForHomeBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getCurrentLocation(){
        if(ActivityCompat.checkSelfPermission(
                requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (requireActivity(),android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),permissionCode)
            return
        }

        val getLocation = mFusedLocationClient.lastLocation.addOnSuccessListener { location ->

            if (location != null){
                currentLocation = location
                ToastUtil.showToast(currentLocation.latitude.toString() + " " + currentLocation.longitude.toString())

                val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                mapFragment?.getMapAsync(callback)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            permissionCode -> if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation()
            }
        }
    }
}