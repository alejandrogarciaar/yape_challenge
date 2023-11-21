package com.john.home_impl.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.john.home_impl.data.repository.HomeRepository
import com.john.home_impl.domain.entities.HomeRecipePreview
import com.john.home_impl.domain.usecases.GetHomeRecipesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class GetHomeRecipesUseCaseTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var repository: HomeRepository

    private lateinit var useCase: GetHomeRecipesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this@GetHomeRecipesUseCaseTest, relaxed = true)
        useCase = GetHomeRecipesUseCase(repository)
    }

    @Test
    fun `Calling useCase should return success response properly`() = runTest {
        coEvery { repository.getReceipts() } returns Result.success(DEFAULT_RECIPES)

        val response = useCase.invoke()

        assertTrue(response.isSuccess)
        assertEquals(1, response.getOrNull()?.size)
    }

    @Test
    fun `Calling useCase should return failure response properly`() = runTest {
        coEvery { repository.getReceipts() } returns Result.failure(Exception())

        val response = useCase.invoke()

        assertTrue(response.isFailure)
    }

    private companion object {
        val DEFAULT_RECIPES = listOf(
            HomeRecipePreview(
                id = 1L,
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
    }
}