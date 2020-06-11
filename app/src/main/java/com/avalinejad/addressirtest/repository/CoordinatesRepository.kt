package com.avalinejad.addressirtest.repository

import com.avalinejad.addressirtest.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoordinatesRepository @Inject constructor(private val apiService: ApiService) {
    //    var lat: Long = 0
//    var lon: Long = 0
    suspend fun getCoordinates(lat: Double, lon: Double) = apiService.getCoordinates(lat, lon).await()
}