package com.john.recipe_detail_impl.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.recipe_detail_impl.domain.usecases.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase,
    private val dispatcher: CoroutineContext
) : ViewModel() {

    private val _uiAction: MutableLiveData<RecipeDetailUiAction> = MutableLiveData()
    val uiAction: LiveData<RecipeDetailUiAction> get() = _uiAction

    private val _uiState: MutableLiveData<RecipeDetailUiState> = MutableLiveData()
    val uiState: MutableLiveData<RecipeDetailUiState> get() = _uiState

    fun getRecipeDetail(id: Long) {
        viewModelScope.launch(dispatcher) {
            _uiAction.postValue(RecipeDetailUiAction.Loading)
            val response = getRecipeDetailUseCase.invoke(id)
            if (response.isSuccess) {
                response.getOrNull()?.let {
                    _uiState.postValue(RecipeDetailUiState.ShowRecipeDetail(details = it))
                } ?: run {
                    _uiState.postValue(RecipeDetailUiState.ShowError)
                }
            } else {
                _uiState.postValue(RecipeDetailUiState.ShowError)
            }
            _uiAction.postValue(RecipeDetailUiAction.Idle)
        }
    }
}