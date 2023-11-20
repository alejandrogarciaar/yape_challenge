package com.john.home_impl.dto

import com.google.gson.annotations.SerializedName

data class HomeRecipePreviewDTO(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("ingredients") val ingredients: List<String>?
)