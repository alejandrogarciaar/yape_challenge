package com.john.home_impl.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.john.home_impl.domain.entities.HomeRecipePreview
import com.john.home_impl.domain.usecases.GetHomeRecipesByTermUseCase
import com.john.home_impl.domain.usecases.GetHomeRecipesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var getHomeRecipesUseCase: GetHomeRecipesUseCase

    @RelaxedMockK
    private lateinit var getHomeRecipesByTermUseCase: GetHomeRecipesByTermUseCase

    @MockK
    private lateinit var uiStateObserver: Observer<HomeUiState>

    @MockK
    private lateinit var uiActionObserver: Observer<HomeUiAction>

    private val testCoroutineContext = UnconfinedTestDispatcher()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this@HomeViewModelTest, relaxed = true)
        viewModel =
            HomeViewModel(
                getHomeRecipesUseCase,
                getHomeRecipesByTermUseCase,
                testCoroutineContext
            ).apply {
                uiState.observeForever(uiStateObserver)
                uiAction.observeForever(uiActionObserver)
            }
    }

    @Test
    fun `Calling getReceipts should notifies the loading state properly`() = runTest {
        coEvery {
            getHomeRecipesUseCase.invoke()
        } returns Result.success(listOf())

        viewModel.getReceipts()

        verify {
            uiActionObserver.onChanged(HomeUiAction.Loading)
            uiActionObserver.onChanged(HomeUiAction.Idle)
        }
    }

    @Test
    fun `Calling getReceipts should notifies home state properly`() = runTest {
        coEvery {
            getHomeRecipesUseCase.invoke()
        } returns Result.success(DEFAULT_RECIPES)

        viewModel.getReceipts()

        verify {
            uiStateObserver.onChanged(HomeUiState.ShowReceipts(DEFAULT_RECIPES))
        }
    }

    @Test
    fun `Calling getReceipts should notifies home empty state properly`() = runTest {
        coEvery {
            getHomeRecipesUseCase.invoke()
        } returns Result.success(emptyList())

        viewModel.getReceipts()

        verify {
            uiStateObserver.onChanged(HomeUiState.ShowEmptyState)
        }
    }

    @Test
    fun `Calling getReceipts should notifies home empty state properly when use case retrieves an error`() =
        runTest {
            coEvery {
                getHomeRecipesUseCase.invoke()
            } returns Result.failure(DEFAULT_ERROR)

            viewModel.getReceipts()

            verify {
                uiStateObserver.onChanged(HomeUiState.ShowError(DEFAULT_ERROR))
            }
        }

    private companion object {
        val DEFAULT_ERROR = Throwable()

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