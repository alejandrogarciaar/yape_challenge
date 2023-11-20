package com.john.recipe_detail_wiring_impl

import com.john.recipe_detail_api.navigation.RecipeDetailsNavigation
import com.john.recipe_detail_wiring_impl.navigation.RecipeDetailsNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RecipeDetailWiringModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface RecipeDetailNavigation {
        @Binds
        fun bindsRecipeDetailNavigation(impl: RecipeDetailsNavigationImpl): RecipeDetailsNavigation
    }
}