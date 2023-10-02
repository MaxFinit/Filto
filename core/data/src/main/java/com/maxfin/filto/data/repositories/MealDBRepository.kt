package com.maxfin.filto.data.repositories

import com.maxfin.filto.common.Result
import com.maxfin.filto.data.mapper.mapToMeal
import com.maxfin.filto.network.MealDBNetwork
import com.maxfin.filto.network.base.NetworkResult
import com.maxfin.filto.network.dto.MealFilterRequestArgs
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealDBRepository @Inject constructor(
    private val mealDBNetwork: MealDBNetwork,
) {

    fun searchMealByName(query: String) = flow {
        emit(Result.Loading(true))
        val source = mealDBNetwork.searchMealByName(query)
        emit(source)
        emit(Result.Loading(false))
    }

    fun filterMeal(
        mealFilterArgs: MealFilterRequestArgs
    ) = flow {
        emit(Result.Loading(true))
        val source = mealDBNetwork.filterMeal(
            mealFilterArgs
        )
        emit(source)
        emit(Result.Loading(false))
    }

    fun randomMeal() = flow {
        emit(Result.Loading(true))
        when (val source = mealDBNetwork.randomMeal()) {
            is NetworkResult.Error -> {
                emit(Result.Error(source.exception))
            }
            is NetworkResult.Success -> {
                emit(Result.Success(source.data.meals.mapToMeal()))
            }
        }
        emit(Result.Loading(false))
    }

    fun detailsMeal(id: String) = flow {
        emit(Result.Loading(true))
        val source = mealDBNetwork.detailsMeal(id)
        emit(source)
        emit(Result.Loading(false))
    }


}