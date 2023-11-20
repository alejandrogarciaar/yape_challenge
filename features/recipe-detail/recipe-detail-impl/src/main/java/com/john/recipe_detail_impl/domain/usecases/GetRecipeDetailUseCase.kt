package com.john.recipe_detail_impl.domain.usecases

import com.john.recipe_detail_impl.data.repository.RecipeDetailRepository
import com.john.recipe_detail_impl.domain.entities.RecipeDetail
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository
) {
    suspend operator fun invoke(id: Long): Result<RecipeDetail> {
        return recipeDetailRepository.getRecipeDetail(id)
    }
}