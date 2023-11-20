package com.john.recipe_detail_impl.domain.entities

import com.john.recipe_detail_impl.domain.entities.Coordinates

data class RecipeDetail(
    val name: String,
    val image: String,
    val coordinates: Coordinates,
    val instructions: List<String>,
    val ingredients: List<String>
)
