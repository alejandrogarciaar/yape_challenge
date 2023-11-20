package com.john.recipe_detail_impl.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.john.recipe_detail_impl.databinding.DetailActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailActivity : AppCompatActivity() {

    private var _binding: DetailActivityBinding? = null
    private val binding: DetailActivityBinding get() = _binding!!

    private var recipeId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DetailActivityBinding.inflate(layoutInflater)
        getExtrasFromIntent()
        setListeners()
        addSubscriptions()
    }

    private fun getExtrasFromIntent() {
        recipeId = intent.extras?.getLong(RECIPE_ID)
    }

    private fun setListeners() {
        binding.checkLocationButton.setOnClickListener {
        }
    }

    private fun addSubscriptions() {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val RECIPE_ID = "RECIPE_ID"
    }
}