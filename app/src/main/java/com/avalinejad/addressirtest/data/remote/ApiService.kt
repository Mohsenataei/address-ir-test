package com.avalinejad.addressirtest.data.remote

import com.avalinejad.addressirtest.data.model.Response
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("rest/hmj/by/point/{lat}/{lon}")
    fun getCoordinates(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double
    ): Deferred<Response>
}