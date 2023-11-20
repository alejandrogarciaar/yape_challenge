package com.john.recipe_detail_impl.dto

import com.google.gson.annotations.SerializedName

data class CoordinatesDTO(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)