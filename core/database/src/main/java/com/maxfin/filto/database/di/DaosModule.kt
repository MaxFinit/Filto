package com.maxfin.filto.database.di

import com.maxfin.filto.database.CoreDatabase
import com.maxfin.filto.database.dao.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: CoreDatabase,
    ): FavoritesDao = database.favoritesDao()

}
