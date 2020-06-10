package com.avalinejad.addressirtest.di

import android.app.Application
import com.avalinejad.addressirtest.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class,
        ActivityBuildersModule::class,
        AndroidSupportInjectionModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent: AndroidInjector<DaggerApplication>{

    @Component.Builder
    interface Builder{
        @BindsInstance

        fun application(app:Application):Builder
        fun appModule(appModule:AppModule):Builder
        fun build(): AppComponent
    }

    fun inject(app: App)


}