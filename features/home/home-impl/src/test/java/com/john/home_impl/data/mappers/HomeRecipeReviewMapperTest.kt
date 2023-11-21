package com.john.home_impl.data.mappers

import com.john.home_impl.domain.entities.HomeRecipePreview
import com.john.home_impl.dto.HomeRecipePreviewDTO
import com.john.home_impl.dto.HomeRecipeResponseDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeRecipeReviewMapperTest {

    @Test
    fun `Calling map should retrieves mapped object properly`() {
        val response = HomeRecipeReviewMapper.map(DEFAULT_SUCCESS_RESPONSE.recipes)

        assertEquals(
            listOf(
                HomeRecipePreview(
                    id = 1L,
                    name = "Spaghetti Bolognese",
                    image = "https://img.freepik.com/free-photo/authentic-italian-pasta_24972-2334.jpg",
                    ingredients = listOf(
                        "400g ground beef",
                        "1 onion",
                        "2 cloves garlic",
                        "1 can (400g) crushed tomatoes",
                        "2 tablespoons tomato paste",
                        "1 tsp dried oregano",
                        "1 tsp dried basil",
                        "Salt and pepper to taste",
                        "300g spaghetti",
                        "Grated Parmesan cheese"
                    )
                ),
                HomeRecipePreview(
                    id = 2L,
                    name = "Chicken Stir-Fry",
                    image = "https://img.freepik.com/free-photo/chicken-stir-fry-vegetables_123827-21544.jpg",
                    ingredients = listOf(
                        "500g chicken breasts",
                        "2 cups broccoli florets",
                        "1 red bell pepper",
                        "1 carrot",
                        "3 tbsp soy sauce",
                        "2 tbsp oyster sauce",
                        "1 tbsp sesame oil",
                        "1 tbsp vegetable oil",
                        "2 cloves garlic",
                        "1 tsp fresh ginger",
                        "Cooked rice"
                    )
                )
            ),
            response
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Calling map should retrieves error when id is null`() {
        val recipe = DEFAULT_SUCCESS_RESPONSE.recipes.first()

        HomeRecipeReviewMapper.map(
            listOf(
                recipe.copy(id = null)
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Calling map should retrieves error when name is null`() {
        val recipe = DEFAULT_SUCCESS_RESPONSE.recipes.first()

        HomeRecipeReviewMapper.map(
            listOf(
                recipe.copy(name = null)
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Calling map should retrieves error when image is null`() {
        val recipe = DEFAULT_SUCCESS_RESPONSE.recipes.first()

        HomeRecipeReviewMapper.map(
            listOf(
                recipe.copy(image = null)
            )
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Calling map should retrieves error when ingredients is null`() {
        val recipe = DEFAULT_SUCCESS_RESPONSE.recipes.first()

        HomeRecipeReviewMapper.map(
            listOf(
                recipe.copy(ingredients = null)
            )
        )
    }

    private companion object {
        val DEFAULT_SUCCESS_RESPONSE = HomeRecipeResponseDTO(
            recipes = listOf(
                HomeRecipePreviewDTO(
                    id = 1L,
                    name = "Spaghetti Bolognese",
                    image = "https://img.freepik.com/free-photo/authentic-italian-pasta_24972-2334.jpg",
                    ingredients = listOf(
                        "400g ground beef",
                        "1 onion",
                        "2 cloves garlic",
                        "1 can (400g) crushed tomatoes",
                        "2 tablespoons tomato paste",
                        "1 tsp dried oregano",
                        "1 tsp dried basil",
                        "Salt and pepper to taste",
                        "300g spaghetti",
                        "Grated Parmesan cheese"
                    )
                ),
                HomeRecipePreviewDTO(
                    id = 2L,
                    name = "Chicken Stir-Fry",
                    image = "https://img.freepik.com/free-photo/chicken-stir-fry-vegetables_123827-21544.jpg",
                    ingredients = listOf(
                        "500g chicken breasts",
                        "2 cups broccoli florets",
                        "1 red bell pepper",
                        "1 carrot",
                        "3 tbsp soy sauce",
                        "2 tbsp oyster sauce",
                        "1 tbsp sesame oil",
                        "1 tbsp vegetable oil",
                        "2 cloves garlic",
                        "1 tsp fresh ginger",
                        "Cooked rice"
                    )
                )
            )
        )
    }
}