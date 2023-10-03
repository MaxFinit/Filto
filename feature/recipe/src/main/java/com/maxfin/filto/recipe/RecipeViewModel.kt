package com.maxfin.filto.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxfin.filto.common.Result
import com.maxfin.filto.data.repositories.MealDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val mealDBRepository: MealDBRepository
) : ViewModel() {

    val uiState: LiveData<RecipeUiState>
        get() = _uiState

    private val _uiState = MutableLiveData<RecipeUiState>().apply {
        value = RecipeUiState()
    }


    fun detailsMeal(idMeal:String ) {
        viewModelScope.launch {
            mealDBRepository.detailsMeal(idMeal).collect {
                when (it) {
                    is Result.Error -> {
                        _uiState.value = _uiState.value?.copy(hasError = true)
                    }

                    is Result.Loading -> {
                        _uiState.value = _uiState.value?.copy(isLoading = it.isLoad)
                    }

                    is Result.Success -> {
                        _uiState.value = _uiState.value?.copy(meal = it.data.first())
                    }
                }
            }
        }
    }

}