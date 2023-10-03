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

    fun getMeal(category: String) = flow {
        emit(Result.Loading(true))

        val source =  if (category.isEmpty()){
            mealDBNetwork.randomMeal()
        }else{
            mealDBNetwork.filterMeal(MealFilterRequestArgs(category = category))
        }

        when (source) {
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