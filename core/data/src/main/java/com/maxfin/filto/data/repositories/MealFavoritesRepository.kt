package com.maxfin.filto.data.repositories

import com.maxfin.filto.common.Result
import com.maxfin.filto.data.mapper.mapEntityToMeal
import com.maxfin.filto.data.mapper.mapToMeal
import com.maxfin.filto.database.dao.FavoritesDao
import com.maxfin.filto.database.entity.FavoritesEntity
import com.maxfin.filto.network.base.NetworkResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealFavoritesRepository @Inject constructor(
    private val favoritesDao: FavoritesDao
) {

    fun getFavorites() = flow {
        emit(Result.Loading(true))

        val source = favoritesDao.getFavorites().mapEntityToMeal()

        emit(Result.Success(source))

        emit(Result.Loading(false))
    }

    fun addToFavorites(
        id: String,
        name: String,
        image: String
    ) = flow {

        emit(Result.Loading(true))
        val source = favoritesDao.insertFavoriteMeal(
            FavoritesEntity(
                id = id,
                name = name,
                image = image
            )
        )
        emit(Result.Success(source))
        emit(Result.Loading(false))
    }


}