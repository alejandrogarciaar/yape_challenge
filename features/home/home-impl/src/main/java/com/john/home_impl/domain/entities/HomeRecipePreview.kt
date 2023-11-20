package com.john.home_impl.domain.entities

data class HomeRecipePreview(
    val id: Long,
    val name: String,
    val image: String,
    val ingredients: List<String>
)