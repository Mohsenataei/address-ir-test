package com.avalinejad.addressirtest

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.avalinejad.addressirtest.bottomSheetDialog.DetailsBottomSheetDialogFragment
import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.data.model.Detail
import com.avalinejad.addressirtest.data.model.Response
import com.avalinejad.addressirtest.di.DaggerViewModelFactory
import com.avalinejad.addressirtest.ui.home.HomeViewModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@SuppressLint("LogNotTimber")
@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MapsActivity : AppCompatActivity(), OnMapReadyCallback, HasAndroidInjector,
    GoogleApiClient.OnConnectionFailedListener {

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    override fun androidInjector(): AndroidInjector<Any?>? = androidInjector

    @Inject
    lateinit var eventBus: EventBus

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }
    private var mGoogleApiClient: GoogleApiClient? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var dialog: DetailsBottomSheetDialogFragment


    private var mLocationPermissionsGranted = false
    private val TAG = "MapActivity"

    private val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    private val COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    private val LOCATION_PERMISSION_REQUEST_CODE = 1234
    private val DEFAULT_ZOOM = 15f


    @SuppressLint("LogNotTimber")
    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_maps)
        getLocationPermission()
        tabs.addTabs()
        var response: Response? = null
        val lat = 35.6892
        val lon = 51.3890
        viewModel.loadData(lat,lon)
        viewModel.coordinates.observe(this, Observer {
            response = it
            Log.d("mapResponse", "$it")
        })
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        updateMapMarkers(response!!.highways)
                        val dialog =
                            DetailsBottomSheetDialogFragment(response?.highways as MutableList<Detail>, getString(R.string.highways))
                        dialog.show(supportFragmentManager, "")
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        updateMapMarkers(response!!.highways)
                         dialog =
                            DetailsBottomSheetDialogFragment(response?.highways as MutableList<Detail>,getString(R.string.highways))
                        dialog.show(supportFragmentManager, "")
                    }
                    1->{
                        updateMapMarkers(response!!.busStops)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.busStops as MutableList<Detail>,getString(R.string.bus_stops))
                        dialog.show(supportFragmentManager, "")
                    }
                    2->{
                        updateMapMarkers(response!!.metroStations)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.metroStations as MutableList<Detail>,getString(R.string.metro_stations))
                        dialog.show(supportFragmentManager, "")
                    }
                    3->{
                        updateMapMarkers(response!!.restaurants)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.restaurants as MutableList<Detail>,getString(R.string.restaurants))
                        dialog.show(supportFragmentManager, "")
                    }
                    4->{
                        updateMapMarkers(response!!.parks)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.parks as MutableList<Detail>,getString(R.string.parks))
                        dialog.show(supportFragmentManager, "")
                    }
                    5->{
                        updateMapMarkers(response!!.cafes)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.cafes as MutableList<Detail>,getString(R.string.cafes))
                        dialog.show(supportFragmentManager, "")
                    }
                    6->{
                        updateMapMarkers(response!!.shoppingMalls)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.shoppingMalls as MutableList<Detail>,getString(R.string.shopping_malls))
                        dialog.show(supportFragmentManager, "")
                    }
                    7->{
                        updateMapMarkers(response!!.mosques)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.mosques as MutableList<Detail>,getString(R.string.mosques))
                        dialog.show(supportFragmentManager, "")
                    }
                    8->{
                        updateMapMarkers(response!!.pharmacies)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.pharmacies as MutableList<Detail>,getString(R.string.pharmacies))
                        dialog.show(supportFragmentManager, "")
                    }
                    9->{
                        updateMapMarkers(response!!.hospitals)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.hospitals as MutableList<Detail>,getString(R.string.hospitals))
                        dialog.show(supportFragmentManager, "")
                    }
                    10->{
                        updateMapMarkers(response!!.schools)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.schools as MutableList<Detail>,getString(R.string.schools))
                        dialog.show(supportFragmentManager, "")
                    }
                    11->{
                        updateMapMarkers(response!!.gyms)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.gyms as MutableList<Detail>,getString(R.string.gyms))
                        dialog.show(supportFragmentManager, "")
                    }
                    12->{
                        updateMapMarkers(response!!.bookstores)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.bookstores as MutableList<Detail>,getString(R.string.bookstores))
                        dialog.show(supportFragmentManager, "")
                    }
                    13->{
                        updateMapMarkers(response!!.flowerShops)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.flowerShops as MutableList<Detail>,getString(R.string.flower_shops))
                        dialog.show(supportFragmentManager, "")
                    }
                    14->{
                        updateMapMarkers(response!!.libraries)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.libraries as MutableList<Detail>,getString(R.string.libraries))
                        dialog.show(supportFragmentManager, "")
                    }
                    15->{
                        updateMapMarkers(response!!.hotels)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.hotels as MutableList<Detail>,getString(R.string.hotels))
                        dialog.show(supportFragmentManager, "")
                    }
                    16->{
                        updateMapMarkers(response!!.parkings)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.parkings as MutableList<Detail>,getString(R.string.parkings))
                        dialog.show(supportFragmentManager, "")
                    }
                    17->{
                        updateMapMarkers(response!!.highways)
                        dialog =
                            DetailsBottomSheetDialogFragment(response?.highways as MutableList<Detail>,getString(R.string.highways))
                        dialog.show(supportFragmentManager, "")
                    }
                }
            }

        })
    }
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (mLocationPermissionsGranted) {
//            getDeviceLocation()
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                !== PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            moveCamera(LatLng(35.6892,51.3890),DEFAULT_ZOOM)
            mMap.isMyLocationEnabled = true
        }

    }

    private fun TabLayout.addTabs() {
        addTab(newTab().setText(R.string.highways))
        addTab(newTab().setText(R.string.bus_stops))
        addTab(newTab().setText(R.string.metro_stations))
        addTab(newTab().setText(R.string.restaurants))
        addTab(newTab().setText(R.string.parks))
        addTab(newTab().setText(R.string.cafes))
        addTab(newTab().setText(R.string.shopping_malls))
        addTab(newTab().setText(R.string.mosques))
        addTab(newTab().setText(R.string.pharmacies))
        addTab(newTab().setText(R.string.hospitals))
        addTab(newTab().setText(R.string.schools))
        addTab(newTab().setText(R.string.gyms))
        addTab(newTab().setText(R.string.bookstores))
        addTab(newTab().setText(R.string.flower_shops))
        addTab(newTab().setText(R.string.libraries))
        addTab(newTab().setText(R.string.hotels))
        addTab(newTab().setText(R.string.parkings))
        addTab(newTab().setText(R.string.all), true)
    }

    private fun getLocationPermission() {
        Log.d(
            TAG,
            "getLocationPermission: getting location permissions"
        )
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                FINE_LOCATION
            ) === PackageManager.PERMISSION_GRANTED
        ) {
            if (ContextCompat.checkSelfPermission(
                    this.applicationContext,
                    COURSE_LOCATION
                ) === PackageManager.PERMISSION_GRANTED
            ) {
                mLocationPermissionsGranted = true
                initMap()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    private fun getDeviceLocation() {
        Log.d(
            TAG,
            "getDeviceLocation: getting the devices current location"
        )
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        try {
            if (mLocationPermissionsGranted) {
                val location = fusedLocationClient.lastLocation
                location.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentLocation = task.result
                        Log.d(TAG,"lat is ${currentLocation!!.latitude} and lon is ${currentLocation.longitude}")
                        moveCamera(
                            LatLng(currentLocation!!.latitude, currentLocation.longitude),
                            DEFAULT_ZOOM
                        )
                        viewModel.loadData(currentLocation.latitude,currentLocation.longitude)
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e(
                TAG,
                "getDeviceLocation: SecurityException: " + e.message
            )
        }
    }

    private fun moveCamera(
        latLng: LatLng,
        zoom: Float
    ) {
        Log.d(
            TAG,
            "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    private fun updateMapMarkers(details: List<Detail>){
        mMap.clear()
        details.forEach {detail->
            mMap.addMarker(MarkerOptions().position(LatLng(detail.lat.toDouble(),detail.lng.toDouble())).title(detail.title))
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
        }
    }
}

