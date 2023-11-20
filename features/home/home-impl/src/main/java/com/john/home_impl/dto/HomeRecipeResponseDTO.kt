package com.john.home_impl.dto

import com.google.gson.annotations.SerializedName

data class HomeRecipeResponseDTO(
    @SerializedName("recipes") val recipes: List<HomeRecipePreviewDTO>
)
