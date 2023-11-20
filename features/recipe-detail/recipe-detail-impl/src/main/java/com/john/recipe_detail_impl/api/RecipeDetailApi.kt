package com.john.recipe_detail_impl.api

import com.john.recipe_detail_impl.dto.RecipeDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeDetailApi {

    @GET(GET_DETAIL_RECIPE)
    suspend fun getRecipeDetail(@Path("id") id: Long = 1L): RecipeDetailDTO

    private companion object {
        const val GET_DETAIL_RECIPE = "/recipe/{id}"
    }
}