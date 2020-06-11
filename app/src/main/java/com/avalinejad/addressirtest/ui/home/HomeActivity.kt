package com.avalinejad.addressirtest.ui.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
//import androidx.activity.
//import androidx.activity.viewModels
import androidx.activity.viewModels
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avalinejad.addressirtest.R
import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.di.DaggerViewModelFactory
import com.avalinejad.addressirtest.repository.CoordinatesRepository
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    override fun androidInjector(): AndroidInjector<Any?>? = androidInjector

    @Inject
    lateinit var eventBus: EventBus

    private val viewModel by viewModels<HomeViewModel> { viewModelFactory }


    @SuppressLint("LogNotTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_home)

        val lat =  35.7247396
        val lon =  51.4217074
        viewModel.loadData(lat, lon)

        viewModel.coordinates.observe(this , Observer {
            Log.d("response","$it")
        })
    }


}
