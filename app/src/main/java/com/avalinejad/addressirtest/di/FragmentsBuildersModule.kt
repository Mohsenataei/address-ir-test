package com.avalinejad.addressirtest.di

import com.avalinejad.addressirtest.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@Module
abstract class FragmentsBuildersModule  {
    @PerActivity
    @ContributesAndroidInjector
    internal abstract fun conributeHomeFragment(): HomeFragment
}