package com.avalinejad.addressirtest

import com.avalinejad.addressirtest.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import dagger.android.DaggerApplication

class App : DaggerApplication(){

    lateinit var appComponent: AppComponent
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        TODO("Not yet implemented")
    }

}