package com.john.recipe_detail_impl.data.repository

import com.john.recipe_detail_impl.domain.entities.RecipeDetail

interface RecipeDetailRepository {
    suspend fun getRecipeDetail(id: Long): Result<RecipeDetail>
}