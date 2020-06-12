package com.avalinejad.addressirtest.di

import com.avalinejad.addressirtest.MapsActivity
import com.avalinejad.addressirtest.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun conributeChooserActivity(): HomeActivity

    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun conributeMapsActivity(): MapsActivity



}