package com.maxfin.filto.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maxfin.filto.data.model.Meal
import com.maxfin.filto.favorites.databinding.HolderFavoritesMealBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class FavoritesMealHolder(private val meal: Meal) :
    AbstractBindingItem<HolderFavoritesMealBinding>() {

    override var tag: Any? = meal.idMeal
    override val type: Int
        get() = R.id.id_favorites_meal_holder

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): HolderFavoritesMealBinding {
        return HolderFavoritesMealBinding.inflate(inflater, parent, false)
    }


    override fun bindView(binding: HolderFavoritesMealBinding, payloads: List<Any>) {
        binding.mealData = meal
        binding.executePendingBindings()
    }


}