package com.john.home_impl.presentation

sealed interface HomeUiAction {
    data object Loading : HomeUiAction
    data object Idle : HomeUiAction
}