package com.maxfin.filto.recipe

import com.maxfin.filto.data.model.Meal

data class RecipeUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val meal : Meal? = null
)
