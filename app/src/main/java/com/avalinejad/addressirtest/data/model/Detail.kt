package com.avalinejad.addressirtest.data.model

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("title")
    val title: String,

    @SerializedName("lat")
    val lat: String,

    @SerializedName("lng")
    val lng: String,

    @SerializedName("distance")
    val distance: String
)