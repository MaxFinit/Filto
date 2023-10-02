package com.maxfin.filto.network

import com.maxfin.filto.common.Result
import com.maxfin.filto.network.base.NetworkResult
import com.maxfin.filto.network.dto.MealFilterRequestArgs
import com.maxfin.filto.network.dto.MealResponse
import retrofit2.Response

interface MealDBDataSource {

    suspend fun searchMealByName(query: String): NetworkResult<MealResponse>

    suspend fun filterMeal(
        mealFilterArgs: MealFilterRequestArgs
    ): NetworkResult<MealResponse>

    suspend fun randomMeal(): NetworkResult<MealResponse>

    suspend fun detailsMeal(id: String)
            : NetworkResult<MealResponse>

}