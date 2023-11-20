package com.john.recipe_detail_impl.data.mappers

import com.john.recipe_detail_impl.domain.entities.Coordinates
import com.john.recipe_detail_impl.domain.entities.RecipeDetail
import com.john.recipe_detail_impl.dto.RecipeDetailDTO

object RecipeDetailMapper {
    fun map(recipeDetail: RecipeDetailDTO): RecipeDetail {
        return RecipeDetail(
            name = requireNotNull(recipeDetail.name) { "RecipeDetailDTO.name is required" },
            image = requireNotNull(recipeDetail.image) { "RecipeDetailDTO.image is required" },
            coordinates = Coordinates(
                longitude = requireNotNull(recipeDetail.coordinates.longitude) { "RecipeDetailDTO.coordinates.longitude" },
                latitude = requireNotNull(recipeDetail.coordinates.latitude) { "RecipeDetailDTO.coordinates.latitude" }
            ),
            ingredients = requireNotNull(recipeDetail.ingredients) { "RecipeDetailDTO.ingredients are required" },
            instructions = requireNotNull(recipeDetail.instructions) { "RecipeDetailDTO.instructions are required" }
        )
    }
}