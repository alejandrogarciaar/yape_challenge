package com.john.recipe_detail_impl.presentation

import com.john.recipe_detail_impl.domain.entities.RecipeDetail

sealed interface RecipeDetailUiState {
    data class ShowRecipeDetail(val details: RecipeDetail) : RecipeDetailUiState
    data object ShowError : RecipeDetailUiState
}