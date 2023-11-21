package com.john.recipe_detail_api.navigation

import android.content.Context

interface RecipeDetailsNavigation {
    fun startRecipeDetail(context: Context, id: Long)
}