package com.maxfin.filto.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxfin.filto.common.Result
import com.maxfin.filto.data.model.Meal
import com.maxfin.filto.data.repositories.MealFavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val mealFavoritesRepository: MealFavoritesRepository
) : ViewModel() {

//    val uiState: LiveData<FavoriteUiState>
//        get() = _uiState
//
//    private val _uiState = MutableLiveData<FavoriteUiState>().apply {
//        value = FavoriteUiState()
//    }

    val favoritesMeal: LiveData<List<Meal>>
        get() = _favoritesMeal

    private val _favoritesMeal = MutableLiveData<List<Meal>>()

    init {
        getFavorites()
    }


    fun getFavorites() {
        viewModelScope.launch {
            mealFavoritesRepository.getFavorites().collect {
                when (it) {
                    is Result.Error -> {}
                    is Result.Loading -> {}
                    is Result.Success -> {
                        _favoritesMeal.value = it.data
                    }
                }
            }
        }
    }

}
