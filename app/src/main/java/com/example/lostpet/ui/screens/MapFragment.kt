package com.example.lostpet.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lostpet.R
import com.example.lostpet.data.model.Pet
import com.example.lostpet.databinding.FragmentMapsBinding
import com.example.lostpet.ui.viewmodels.MapViewModel
import com.example.lostpet.ui.viewmodels.ViewModelFactory
import com.example.lostpet.utils.PermissionUtils
import com.example.lostpet.utils.PermissionUtils.PermissionDeniedDialog.Companion.newInstance
import com.example.lostpet.utils.PermissionUtils.isPermissionGranted
import com.example.lostpet.utils.appComponent
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject


class MapFragment : Fragment(), GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener, OnMapReadyCallback,
    ActivityCompat.OnRequestPermissionsResultCallback{

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = checkNotNull(_binding) { "Binding is null" }


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by viewModels<MapViewModel> { viewModelFactory }
//   private val viewModel by viewModels<MapViewModel> {
//       ViewModelProvider(requireActivity(), viewModelFactory)[MapViewModel::class.java]
//   }


    private var isCheckedFABOnMap = false
    private lateinit var map: GoogleMap
    private var permissionLocationDenied = false
    private var permissionCallPhoneDenied = false
    private var permissionCallPhoneAccept = false


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireContext().appComponent.inject(this)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        map.clear()
        googleMap.uiSettings.isMapToolbarEnabled = false
        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMyLocationClickListener(this)
        enableMyLocation()
        addMarker(map)
        lifecycleScope.launchWhenResumed {
            viewModel.pets.collect{
                for(pet in viewModel.pets.value){
                    setMarker(map, pet)
                }
            }
        }
        slideBottomSheetDialogFragment(map)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        viewModel.getUsersAndPets()


        val fab = binding.fabAdd
        fab.setOnClickListener {
            isCheckedFABOnMap = !isCheckedFABOnMap
            }
        }
    // Установка меток
    private fun setMarker(mMap: GoogleMap, pet: Pet){
        val marker = mMap.addMarker(
            MarkerOptions()
                .title(pet.petType)
                .position(LatLng(pet.petLatitude.toDouble(),pet.petLongitude.toDouble()))
        )
        marker?.tag = pet
    }

    private fun slideBottomSheetDialogFragment (mMap: GoogleMap){
        mMap.setOnMarkerClickListener(object: GoogleMap.OnMarkerClickListener{
            override fun onMarkerClick(marker: Marker): Boolean {
                val pet: Pet = (marker.tag) as Pet
                val bundle = Bundle()
                bundle.putParcelable("pet", pet)
                lifecycleScope.launchWhenResumed {
                    viewModel.getUser(pet.petUserId)
                    viewModel.user.collect{
                        bundle.putString("userPhone", it.userPhone)
                    }
                }
                if(bundle.getString("userPhone", "0").length < 11){
                    return true
                }
                findNavController().navigate(R.id.action_mapFragment_to_detailMarkerFragment, bundle)
                return true
            }
        })
    }

    // Добавление маркера
    private fun addMarker(mMap: GoogleMap){
         mMap.setOnMapClickListener(object: GoogleMap.OnMapClickListener {
            override fun onMapClick(latLng: LatLng) {
                if(isCheckedFABOnMap){
                    isCheckedFABOnMap = false
                    val bundle = Bundle()
                    bundle.putString("longitude", latLng.longitude.toString())
                    bundle.putString("latitude", latLng.latitude.toString())
                    findNavController().navigate(R.id.action_mapFragment_to_addMarkFragment, bundle)
                }
            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */

    private fun enableCallPhone() {

        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                permissionCallPhoneAccept = true
                return
        }

        // 2. If if a permission rationale dialog should be shown
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            )
        ) {
            activity?.let {
                PermissionUtils.RationaleDialog.newInstance(
                    CALL_PHONE_PERMISSION_REQUEST_CODE, true
                ).show(it.supportFragmentManager, "dialogCallPhone")
            }
            return
        }

        // 3. Otherwise, request permission
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.CALL_PHONE,
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {

        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            map.isMyLocationEnabled = true
            return
        }

        // 2. If if a permission rationale dialog should be shown
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        ) {
            activity?.let {
                PermissionUtils.RationaleDialog.newInstance(
                    LOCATION_PERMISSION_REQUEST_CODE, true
                ).show(it.supportFragmentManager, "dialog")
            }
            return
        }

        // 3. Otherwise, request permission
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(requireContext(), "MyLocation button clicked", Toast.LENGTH_SHORT)
            .show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(requireContext(), "Current location:\n$location", Toast.LENGTH_LONG)
            .show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE || requestCode != CALL_PHONE_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
            return
        }

        if (isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) || isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )|| isPermissionGranted(
                permissions,
                grantResults,
                Manifest.permission.CALL_PHONE
            )
        ) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation()
            enableCallPhone()
        } else {
            // Permission was denied. Display an error message
            // Display the missing permission error dialog when the fragments resume.
            permissionLocationDenied = true
            permissionCallPhoneDenied = true
        }
    }

    override fun onResume() {
        super.onResume()
        if (permissionLocationDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError()
            permissionLocationDenied = false
        }
        if(permissionCallPhoneDenied){
            showMissingPermissionCallPhoneError()
            permissionCallPhoneDenied = false
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private fun showMissingPermissionError() {
        activity?.let {
            newInstance(true).show(it.supportFragmentManager, "dialog")
        }
    }

    private fun showMissingPermissionCallPhoneError() {
        activity?.let {
            newInstance(true).show(it.supportFragmentManager, "dialog1")
        }
    }

    companion object {
        /**
         * Request code for location permission request.
         *
         * @see .onRequestPermissionsResult
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val CALL_PHONE_PERMISSION_REQUEST_CODE = 2
    }

}