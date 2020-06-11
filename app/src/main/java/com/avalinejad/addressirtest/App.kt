package com.avalinejad.addressirtest

import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.di.AppComponent
import com.avalinejad.addressirtest.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class App : DaggerApplication(){


    @Inject
    lateinit var eventBus: EventBus
    lateinit var appComponent: AppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>  = appComponent

    override fun onCreate() {
        AppInjector.initInjector(this)
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}