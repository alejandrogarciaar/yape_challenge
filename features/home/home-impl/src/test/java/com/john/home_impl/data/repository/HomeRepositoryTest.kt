package com.john.home_impl.data.repository

import com.john.home_impl.api.HomeApi
import com.john.home_impl.data.mappers.HomeRecipeReviewMapper
import com.john.home_impl.data.repository.impl.HomeRepositoryImpl
import com.john.home_impl.dto.HomeRecipePreviewDTO
import com.john.home_impl.dto.HomeRecipeResponseDTO
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import java.net.SocketTimeoutException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryTest {

    @RelaxedMockK
    private lateinit var homeApi: HomeApi

    @MockK
    private lateinit var httpException: HttpException

    private lateinit var repository: HomeRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this@HomeRepositoryTest, relaxed = true)
        repository = HomeRepositoryImpl(homeApi)
    }

    @Test
    fun `Calling getReceipts should return success result properly`() = runTest {
        coEvery { homeApi.getRecipes() } returns DEFAULT_SUCCESS_RESPONSE

        val result = repository.getReceipts()

        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()?.size)
        assertEquals(
            HomeRecipeReviewMapper.map(DEFAULT_SUCCESS_RESPONSE.recipes),
            result.getOrNull()
        )
    }

    @Test
    fun `Calling getReceipts should return failure result properly when service throws SocketTimeoutException`() =
        runTest {
            coEvery { homeApi.getRecipes() } throws SocketTimeoutException()

            val result = repository.getReceipts()

            assertTrue(result.isFailure)
        }

    @Test
    fun `Calling getReceipts should return failure result when service throws HttpException`() =
        runTest {
            coEvery { homeApi.getRecipes() } throws httpException

            val result = repository.getReceipts()

            assertTrue(result.isFailure)
        }

    @Test
    fun `Calling getReceipts should return failure result when service throws Exception`() =
        runTest {
            coEvery { homeApi.getRecipes() } throws Exception()

            val result = repository.getReceipts()

            assertTrue(result.isFailure)
        }

    companion object {
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