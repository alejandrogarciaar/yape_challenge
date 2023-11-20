package com.john.recipe_detail_impl.di

import com.john.recipe_detail_impl.api.RecipeDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RecipeDetailNetworkModule {

    @Provides
    fun providesDetailRecipeApi(retrofit: Retrofit): RecipeDetailApi {
        return retrofit.create(RecipeDetailApi::class.java)
    }
}