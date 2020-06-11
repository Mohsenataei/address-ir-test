package com.avalinejad.addressirtest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.ViewModelProviders
import com.avalinejad.addressirtest.R
import com.avalinejad.addressirtest.extentions.*
import com.avalinejad.addressirtest.ui.base.BaseFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar.initToolbar()
        collapsingToolbar.initCollapsingToolbar(toolbar)

        toolbar.title = getString(R.string.toolbar_name)
        tab.addTabs()
        collapsingToolbar.updateLayoutParams<AppBarLayout.LayoutParams> {
            height = getActionBarHeight() + getStatusBarHeight()
        }
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