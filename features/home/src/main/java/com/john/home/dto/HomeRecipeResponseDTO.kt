package com.john.home.dto

import com.google.gson.annotations.SerializedName

data class HomeRecipeResponseDTO(
    @SerializedName("recipes") val recipes: List<HomeRecipePreviewDTO>
)
