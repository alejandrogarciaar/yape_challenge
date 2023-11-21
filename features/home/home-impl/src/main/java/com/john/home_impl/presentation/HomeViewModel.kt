package com.john.home_impl.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.home_impl.domain.entities.HomeRecipePreview
import com.john.home_impl.domain.usecases.GetHomeRecipesByTermUseCase
import com.john.home_impl.domain.usecases.GetHomeRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeRecipesUseCase: GetHomeRecipesUseCase,
    private val getHomeRecipesByTermUseCase: GetHomeRecipesByTermUseCase,
    private val dispatcher: CoroutineContext
) : ViewModel() {

    private val _uiState: MutableLiveData<HomeUiState> = MutableLiveData()
    val uiState: LiveData<HomeUiState> get() = _uiState

    private val _uiAction: MutableLiveData<HomeUiAction> = MutableLiveData()
    val uiAction: LiveData<HomeUiAction> get() = _uiAction

    private var recipes: List<HomeRecipePreview> = emptyList()

    fun getReceipts() {
        viewModelScope.launch(dispatcher) {
            _uiAction.postValue(HomeUiAction.Loading)
            val response = getHomeRecipesUseCase.invoke()
            if (response.isSuccess) {
                response.getOrNull()?.let { remoteReceipts ->
                    recipes = remoteReceipts
                    if (recipes.isNotEmpty()) {
                        _uiState.postValue(HomeUiState.ShowReceipts(recipes = recipes))
                    } else {
                        _uiState.postValue(HomeUiState.ShowEmptyState)
                    }
                } ?: run {
                    _uiState.postValue(HomeUiState.ShowEmptyState)
                }
            } else {
                _uiState.postValue(
                    HomeUiState.ShowError(
                        response.exceptionOrNull() ?: Throwable(
                            DEFAULT_ERROR_MESSAGE
                        )
                    )
                )
            }
            _uiAction.postValue(HomeUiAction.Idle)
        }
    }

    fun searchByTerm(term: String = EMPTY) {
        viewModelScope.launch(dispatcher) {
            if (term.isNotEmpty()) {
                val filteredRecipes = getHomeRecipesByTermUseCase.invoke(recipes, term)
                if (filteredRecipes.isNotEmpty()) {
                    _uiState.postValue(HomeUiState.ShowReceipts(recipes = filteredRecipes))
                } else {
                    _uiState.postValue(HomeUiState.ShowEmptyState)
                }
            } else {
                _uiState.postValue(HomeUiState.ShowReceipts(recipes = recipes))
            }
        }
    }

    private companion object {
        const val EMPTY = ""
        const val DEFAULT_ERROR_MESSAGE = "Something went wrong"
    }
}