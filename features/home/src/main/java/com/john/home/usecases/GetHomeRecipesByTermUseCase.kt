package com.john.home.usecases

import com.john.home.domain.HomeRecipePreview
import javax.inject.Inject

class GetHomeRecipesByTermUseCase @Inject constructor() {
    operator fun invoke(recipes: List<HomeRecipePreview>, term: String): List<HomeRecipePreview> {
        if (term.isBlank()) return recipes
        return recipes.filter {
            it.name.lowercase().contains(term.lowercase()) || it.ingredients.contains(term)
        }
    }
}