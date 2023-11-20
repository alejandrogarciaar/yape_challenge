package com.john.recipe_detail_impl.presentation

sealed interface RecipeDetailUiAction {
    data object Idle : RecipeDetailUiAction
    data object Loading : RecipeDetailUiAction
}