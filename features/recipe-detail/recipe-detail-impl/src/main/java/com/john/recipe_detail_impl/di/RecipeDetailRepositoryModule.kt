package com.john.recipe_detail_impl.di

import com.john.recipe_detail_impl.data.repository.RecipeDetailRepository
import com.john.recipe_detail_impl.data.repository.impl.RecipeDetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RecipeDetailRepositoryModule {
    @Binds
    fun bindsRecipeDetailRepository(recipeDetailRepository: RecipeDetailRepositoryImpl): RecipeDetailRepository
}