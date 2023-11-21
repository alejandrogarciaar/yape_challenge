package com.john.recipe_detail_impl.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.john.recipe_detail_impl.databinding.DetailActivityBinding
import com.john.recipe_detail_impl.domain.entities.Coordinates
import com.john.recipe_detail_impl.presentation.RecipeDetailUiAction.Idle
import com.john.recipe_detail_impl.presentation.RecipeDetailUiAction.Loading
import com.john.recipe_detail_impl.presentation.RecipeDetailUiState.ShowError
import com.john.recipe_detail_impl.presentation.RecipeDetailUiState.ShowRecipeDetail
import com.john.ui_components.utils.renderImageFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailActivity : AppCompatActivity() {

    private val viewModel: RecipeDetailViewModel by viewModels()

    private var _binding: DetailActivityBinding? = null
    private val binding: DetailActivityBinding get() = _binding!!

    private var recipeId: Long? = null
    private var coordinates: Coordinates? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getExtrasFromIntent()
        setListeners()
        addSubscriptions()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        recipeId?.let {
            viewModel.getRecipeDetail(it)
        }
    }

    private fun getExtrasFromIntent() {
        recipeId = intent.extras?.getLong(RECIPE_ID)
    }

    private fun setListeners() {
        binding.checkLocationButton.setOnClickListener {
            coordinates?.let {
                startActivity(Intent(this, RecipeDetailMapActivity::class.java).apply {
                    putExtra(RecipeDetailMapActivity.COORDINATES_KEY, it)
                })
            }
        }
    }

    private fun addSubscriptions() {
        viewModel.uiAction.observe(this) { action ->
            when (action) {
                is Loading -> binding.progressBar.isVisible = true
                is Idle -> binding.progressBar.isVisible = false
            }
        }

        viewModel.uiState.observe(this) { state ->
            when (state) {
                is ShowRecipeDetail -> {
                    binding.recipeNameTextView.text = state.details.name
                    renderIngredients(state.details.ingredients)
                    renderInstructions(state.details.instructions)
                    binding.recipeImageView.renderImageFromUrl(url = state.details.image)
                    coordinates = state.details.coordinates
                    binding.detailsContainer.isVisible = true
                }

                is ShowError -> {
                    binding.errorTextView.isVisible = true
                }
            }
        }
    }

    private fun renderIngredients(ingredients: List<String>) {
        val stringBuilder = StringBuilder()
        ingredients.forEach {
            stringBuilder.appendLine("- $it")
        }
        binding.recipeIngredientsTextView.text = stringBuilder.toString()
    }

    private fun renderInstructions(instructions: List<String>) {
        val stringBuilder = StringBuilder()
        instructions.forEach {
            stringBuilder.appendLine("- $it")
        }
        binding.recipeInstructionsTextView.text = stringBuilder.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val RECIPE_ID = "RECIPE_ID"
    }
}