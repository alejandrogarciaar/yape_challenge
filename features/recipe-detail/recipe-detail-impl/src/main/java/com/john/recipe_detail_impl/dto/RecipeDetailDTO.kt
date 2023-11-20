package com.john.recipe_detail_impl.dto

import com.google.gson.annotations.SerializedName
import com.john.recipe_detail_impl.dto.CoordinatesDTO

data class RecipeDetailDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("ingredients") val ingredients: List<String>,
    @SerializedName("instructions") val instructions: List<String>,
    @SerializedName("coordinates") val coordinates: CoordinatesDTO
)
