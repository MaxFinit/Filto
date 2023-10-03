package com.maxfin.filto.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maxfin.filto.database.entity.FavoritesEntity

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM FavoritesEntity ORDER BY id DESC")
    suspend fun getFavorites() : List<FavoritesEntity>

    @Query("SELECT * FROM FavoritesEntity WHERE id = :id")
    suspend fun getFavoriteMeal(id : Int) : FavoritesEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMeal(mealEntity: FavoritesEntity)

    @Delete
    suspend fun deleteFavoriteMeal(mealEntity: FavoritesEntity)

    @Delete
    suspend fun deleteFavorites(mealEntities: List<FavoritesEntity>)

}