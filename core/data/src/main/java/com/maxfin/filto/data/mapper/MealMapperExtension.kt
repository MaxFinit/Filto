package com.maxfin.filto.data.mapper

import com.maxfin.filto.data.model.Meal
import com.maxfin.filto.database.entity.FavoritesEntity
import com.maxfin.filto.network.dto.MealDto

fun List<MealDto>.mapToMeal() : List<Meal>{

    val listMeal = mutableListOf<Meal>()
    this.forEach {
        listMeal.add(Meal(
            idMeal = it.idMeal,
            strMeal = it.strMeal,
            strDrinkAlternate = it.strDrinkAlternate,
            strCategory = it.strCategory,
            strArea = it.strArea,
            strInstructions = it.strInstructions,
            strMealThumb = it.strMealThumb,
            strTags = it.strTags,
            strYoutube = it.strYoutube,
            strIngredient = mapOf(
                it.strIngredient1 to it.strMeasure1,
                it.strIngredient2 to it.strMeasure2,
                it.strIngredient3 to it.strMeasure3,
                it.strIngredient4 to it.strMeasure4,
                it.strIngredient5 to it.strMeasure5,
                it.strIngredient6 to it.strMeasure6,
                it.strIngredient7 to it.strMeasure7,
                it.strIngredient8 to it.strMeasure8,
                it.strIngredient9 to it.strMeasure9,
                it.strIngredient10 to it.strMeasure10,
                it.strIngredient11 to it.strMeasure11,
                it.strIngredient12 to it.strMeasure12,
                it.strIngredient13 to it.strMeasure13,
                it.strIngredient14 to it.strMeasure14,
                it.strIngredient15 to it.strMeasure15,
                it.strIngredient16 to it.strMeasure16,
                it.strIngredient17 to it.strMeasure17,
                it.strIngredient18 to it.strMeasure18,
                it.strIngredient19 to it.strMeasure19,
                it.strIngredient20 to it.strMeasure20
            ),
            strSource = it.strSource,
            strImageSource = it.strImageSource,
            strCreativeCommonsConfirmed = it.strCreativeCommonsConfirmed,
            dateModified = it.dateModified

        ))
    }
    return listMeal
}

fun List<FavoritesEntity>.mapEntityToMeal() : List<Meal>{
    val listMeal = mutableListOf<Meal>()
    this.forEach {
        listMeal.add(Meal(
         idMeal = it.id,
            strMeal =  it.name,
            strMealThumb =  it.image,
            strDrinkAlternate = null,
            strCategory = null,
            strArea = null,
            strInstructions = null,
            strTags = null,
            strYoutube = null,
            strIngredient = mapOf(),
            strSource = null,
            strImageSource = null,
            strCreativeCommonsConfirmed = null,
            dateModified = null))
    }
    return listMeal
}