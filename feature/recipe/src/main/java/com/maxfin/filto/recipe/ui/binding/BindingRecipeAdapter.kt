package com.maxfin.filto.recipe.ui.binding

import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.maxfin.filto.recipe.databinding.ViewIngredientsBinding

@BindingAdapter("formatIngredients")
fun LinearLayout.formatIngredients(ingredients: Map<String, String>?) {
    if (ingredients != null) {
        val inflater = LayoutInflater.from(this.context)

        for (ingredient in ingredients) {
            val achievementView = ViewIngredientsBinding.inflate(inflater, this, true)

            achievementView.text.text = "${ingredient.key} - ${ingredient.value}"
        }
    }
}