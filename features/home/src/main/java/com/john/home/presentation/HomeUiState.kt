package com.john.home.presentation

import com.john.home.domain.HomeRecipePreview

sealed interface HomeUiState {
    data class ShowReceipts(
        val recipes: List<HomeRecipePreview>
    ) : HomeUiState

    data object ShowEmptyState : HomeUiState

    data class ShowError(
        val throwable: Throwable
    ) : HomeUiState
}