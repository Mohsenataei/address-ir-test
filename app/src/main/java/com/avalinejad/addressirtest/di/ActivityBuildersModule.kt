package com.avalinejad.addressirtest.di

import com.avalinejad.addressirtest.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun conributeChooserActivity(): MainActivity
}