package com.maxfin.filto.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxfin.filto.common.Result
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

    private val _uiState = MutableLiveData<FeedUiState>().apply {
        value = FeedUiState()
    }

    val currentMeal: LiveData<Meal>
        get() = _currentMeal

    private val _currentMeal = MutableLiveData<Meal>()

    init {
        getMeal()
    }


    fun getMeal() {
        viewModelScope.launch {
            mealDBRepository.getMeal(_uiState.value?.category.toString()).collect {
                when (it) {
                    is Result.Error -> {
                        _uiState.value = _uiState.value?.copy(hasError = true)
                    }

                    is Result.Loading -> {
                        _uiState.value = _uiState.value?.copy(isLoading = it.isLoad)
                    }

                    is Result.Success -> {
                        _currentMeal.value = it.data.random()
                    }
                }
            }
        }
    }

    fun addToFavorites() {
        viewModelScope.launch {
            mealDBRepository.addToFavorites(
                id = _currentMeal.value!!.idMeal!!,
                name = _currentMeal.value!!.strMeal!!,
                image = _currentMeal.value!!.strMealThumb!!
            ).collect {
                when(it){
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {}
                }
            }

        }
    }


    fun setCategory(category: String) {
        _uiState.value = _uiState.value?.copy(category = category)
        getMeal()
    }


}
