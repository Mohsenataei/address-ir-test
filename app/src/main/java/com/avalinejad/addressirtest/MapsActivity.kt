package com.avalinejad.addressirtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.avalinejad.addressirtest.extentions.initCollapsingToolbar
import com.avalinejad.addressirtest.extentions.initToolbar

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
//        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
//        supportActionBar?.setCustomView(R.layout.custom_toolbarr)
//        toolbar.initToolbar()
//        collapsingToolbar.initCollapsingToolbar(toolbar)
//
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
//        toolbar.title = getString(R.string.toolbar_name)
        tabs.addTabs()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun TabLayout.addTabs(){
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
        addTab(newTab().setText(R.string.all),true)

    }
}
