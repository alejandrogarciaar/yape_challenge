package com.john.home_impl.presentation

import com.john.home_impl.domain.entities.HomeRecipePreview

sealed interface HomeUiState {
    data class ShowReceipts(
        val recipes: List<HomeRecipePreview>
    ) : HomeUiState

    data object ShowEmptyState : HomeUiState

    data class ShowError(
        val throwable: Throwable
    ) : HomeUiState
}