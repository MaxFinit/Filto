package com.maxfin.filto.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoritesEntity(

    @PrimaryKey
    val id: String,
    val name: String,
    val image: String)
