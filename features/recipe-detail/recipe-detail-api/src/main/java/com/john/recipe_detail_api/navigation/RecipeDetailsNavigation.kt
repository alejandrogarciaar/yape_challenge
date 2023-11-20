package com.john.recipe_detail_api.navigation

import android.content.Context

interface RecipeDetailsNavigation {
    fun startNavigation(context: Context, id: Long)
}