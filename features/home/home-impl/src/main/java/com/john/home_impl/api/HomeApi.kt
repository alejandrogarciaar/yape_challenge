package com.john.home_impl.api

import com.john.home_impl.dto.HomeRecipeResponseDTO
import retrofit2.http.GET

interface HomeApi {

    @GET("recipes")
    suspend fun getRecipes(): HomeRecipeResponseDTO
}