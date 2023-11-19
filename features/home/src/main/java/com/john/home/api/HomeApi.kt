package com.john.home.api

import com.john.home.dto.HomeRecipeResponseDTO
import retrofit2.http.GET

interface HomeApi {

    @GET("recipes")
    suspend fun getRecipes(): HomeRecipeResponseDTO
}