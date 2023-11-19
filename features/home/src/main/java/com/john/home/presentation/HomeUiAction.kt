package com.john.home.presentation

sealed interface HomeUiAction {
    data object Loading : HomeUiAction
    data object Idle : HomeUiAction
}