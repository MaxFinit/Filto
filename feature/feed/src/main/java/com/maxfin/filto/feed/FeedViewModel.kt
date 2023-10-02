package com.maxfin.filto.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxfin.filto.common.Result
import com.maxfin.filto.data.mapper.mapToMeal
import com.maxfin.filto.data.model.Meal
import com.maxfin.filto.data.repositories.MealDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val mealDBRepository: MealDBRepository
) : ViewModel() {

    val uiState: LiveData<FeedUiState>
        get() = _uiState

    private val _uiState = MutableLiveData<FeedUiState>()

    val mealList: LiveData<List<Meal>>
        get() = _mealList

    private val _mealList = MutableLiveData<List<Meal>>()

    init {
        getMeal()
    }


    fun getMeal() {
        viewModelScope.launch {
            mealDBRepository.randomMeal().collect {
                when (it) {
                    is Result.Error -> {
                        _uiState.value = _uiState.value?.copy(hasError = true)
                    }

                    is Result.Loading -> {
                        _uiState.value = _uiState.value?.copy(isLoading = it.isLoad)
                    }

                    is Result.Success -> {
                        _mealList.value = it.data
                    }
                }
            }
        }


    }


}