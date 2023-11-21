package com.john.home_impl.api

import com.john.home_impl.dto.HomeRecipeResponseDTO
import retrofit2.http.GET

interface HomeApi {

    @GET(RECIPES_URL)
    suspend fun getRecipes(): HomeRecipeResponseDTO

    private companion object {
        const val RECIPES_URL = "recipes"
    }
}