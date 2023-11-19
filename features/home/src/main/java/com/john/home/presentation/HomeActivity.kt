package com.john.home.presentation

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.john.home.databinding.HomeActivityBinding
import com.john.home.presentation.HomeUiAction.Idle
import com.john.home.presentation.HomeUiAction.Loading
import com.john.home.presentation.HomeUiState.ShowEmptyState
import com.john.home.presentation.HomeUiState.ShowError
import com.john.home.presentation.HomeUiState.ShowReceipts
import com.john.home.presentation.adapters.HomeRecipePreviewAdapter
import com.john.navigation.DetailNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var detailNavigation: DetailNavigation

    private var _binding: HomeActivityBinding? = null
    private val binding: HomeActivityBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: HomeRecipePreviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addSubscriptions()
        setupRecyclerView()
        setupAdapter()
        setListeners()
        getReceipts()
    }

    private fun setupRecyclerView() {
        binding.resultsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.resultsRecyclerView.setHasFixedSize(true)
    }

    private fun setupAdapter() {
        adapter = HomeRecipePreviewAdapter(
            onReceiptClicked = {
                detailNavigation.goToDetail(this, it)
            }
        )
        binding.resultsRecyclerView.adapter = adapter
    }

    private fun setListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(input: String?): Boolean {
                input?.let { viewModel.searchByTerm(input) }
                return false
            }

            override fun onQueryTextChange(input: String?): Boolean {
                input?.let {
                    if (it.isEmpty()) {
                        viewModel.searchByTerm()
                    }
                }
                return false
            }
        })
    }

    private fun addSubscriptions() {
        viewModel.uiAction.observe(this) { action ->
            when (action) {
                is Idle -> binding.progressBar.isVisible = false
                is Loading -> binding.progressBar.isVisible = true
            }
        }

        viewModel.uiState.observe(this) { state ->
            when (state) {
                is ShowReceipts -> {
                    adapter.submitList(state.recipes)
                    binding.somethingWentWrong.isVisible = false
                    binding.noResultsTextView.isVisible = false
                }

                is ShowError -> {
                    binding.somethingWentWrong.isVisible = true
                    adapter.submitList(null)
                }

                is ShowEmptyState -> {
                    binding.noResultsTextView.isVisible = true
                    adapter.submitList(null)
                }
            }
        }
    }

    private fun getReceipts() {
        viewModel.getReceipts()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}