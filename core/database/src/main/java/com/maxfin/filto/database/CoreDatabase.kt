package com.maxfin.filto.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxfin.filto.database.dao.FavoritesDao
import com.maxfin.filto.database.entity.FavoritesEntity


@Database(entities = [FavoritesEntity::class], version = 1, exportSchema = true)
abstract class CoreDatabase : RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao

}