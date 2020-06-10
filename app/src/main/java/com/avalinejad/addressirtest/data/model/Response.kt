package com.avalinejad.addressirtest.data.model

import com.google.gson.annotations.SerializedName

data class Response(

    @SerializedName("highways")
    val highways: List<Detail>,

    @SerializedName("bus_stops")
    val busStops: List<Detail>,

    @SerializedName("metro_stations")
    val metroStations: List<Detail>,

    @SerializedName("restaurants")
    val restaurants: List<Detail>,

    @SerializedName("parks")
    val parks: List<Detail>,

    @SerializedName("cafes")
    val cafes: List<Detail>,

    @SerializedName("shopping_malls")
    val shoppingMalls: List<Detail>,

    @SerializedName("mosques")
    val mosques: List<Detail>,

    @SerializedName("pharmacies")
    val pharmacies: List<Detail>,

    @SerializedName("hospitals")
    val hospitals: List<Detail>,

    @SerializedName("schools")
    val schools: List<Detail>,

    @SerializedName("gyms")
    val gyms: List<Detail>,

    @SerializedName("bookstores")
    val bookstores: List<Detail>,

    @SerializedName("flower_shops")
    val flowerShops: List<Detail>,

    @SerializedName("libraries")
    val libraries: List<Detail>,

    @SerializedName("hotels")
    val hotels: List<Detail>,

    @SerializedName("parkings")
    val parkings: List<Detail>





    )