package com.john.recipe_detail_wiring_impl.navigation

import android.content.Context
import android.content.Intent
import com.john.recipe_detail_api.navigation.RecipeDetailsNavigation
import com.john.recipe_detail_impl.presentation.RecipeDetailActivity
import javax.inject.Inject

class RecipeDetailsNavigationImpl @Inject constructor() : RecipeDetailsNavigation {
    override fun startNavigation(context: Context, id: Long) {
        context.startActivity(Intent(context, RecipeDetailActivity::class.java).apply {
            putExtra(RecipeDetailActivity.RECIPE_ID, id)
        })
    }
}