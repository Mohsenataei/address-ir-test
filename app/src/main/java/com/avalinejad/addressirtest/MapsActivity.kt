package com.avalinejad.addressirtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.Observer
import com.avalinejad.addressirtest.bottomSheetDialog.DetailsBottomSheetDialogFragment
import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.data.model.Detail
import com.avalinejad.addressirtest.data.model.Response
import com.avalinejad.addressirtest.di.DaggerViewModelFactory
import com.avalinejad.addressirtest.extentions.initCollapsingToolbar
import com.avalinejad.addressirtest.extentions.initToolbar
import com.avalinejad.addressirtest.ui.home.HomeViewModel

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, HasAndroidInjector {

    private lateinit var mMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    override fun androidInjector(): AndroidInjector<Any?>? = androidInjector

    @Inject
    lateinit var eventBus: EventBus

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    private lateinit var coordinates: Response


    @SuppressLint("LogNotTimber")
    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_maps)
        val lat =  35.7247396
        val lon =  51.4217074
        viewModel.loadData(lat,lon)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        tabs.addTabs()
        var response: Response? = null

        viewModel.coordinates.observe(this, Observer {
            response = it
            Log.d("mapResponse","$it")
        })
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        val dialog = DetailsBottomSheetDialogFragment(response?.bookstores as MutableList<Detail>)
                        dialog.show(supportFragmentManager, "")
                    }
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        val dialog = DetailsBottomSheetDialogFragment(response?.hotels as MutableList<Detail>)
                        dialog.show(supportFragmentManager, "")
                    }
                }
            }

        })
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
}
