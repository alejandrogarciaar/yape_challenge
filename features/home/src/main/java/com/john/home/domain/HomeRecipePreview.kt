package com.john.home.domain

data class HomeRecipePreview(
    val id: Long,
    val name: String,
    val image: String,
    val ingredients: List<String>
)