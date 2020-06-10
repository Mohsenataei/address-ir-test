package com.avalinejad.addressirtest.di

import android.content.Context
import com.avalinejad.addressirtest.App
import com.avalinejad.addressirtest.BuildConfig
import com.avalinejad.addressirtest.bus.EventBus
import com.avalinejad.addressirtest.data.remote.ApiService
import com.avalinejad.addressirtest.util.GsonFactory
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    companion object {
        const val BASE_URL = "https://bo.hichestan.org/"
    }

    @Singleton
    @Provides
    internal fun provideApplication(): App = app

    @Singleton
    @Provides
    internal fun provideContext(): Context = app.applicationContext

    @FlowPreview
    @ExperimentalCoroutinesApi
    @Provides
    @Singleton
    internal fun eventBus() = EventBus.instance

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonFactory.instance.singletonGson

    @Provides
    @Singleton
    internal fun provieOkhttpClient() = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor()
        .addLogger()
        .build()


    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson:Gson) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()


    @Provides
    @Singleton
    internal fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    private fun OkHttpClient.Builder.addInterceptor() = apply {
        addInterceptor { chain ->
            chain.proceed(chain.request())
        }
    }

    private fun OkHttpClient.Builder.addLogger() = apply {
        if (BuildConfig.DEBUG) {
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }
}