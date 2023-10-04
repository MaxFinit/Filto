package com.maxfin.filto.feed.ui.binding

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.maxfin.filto.feed.databinding.ViewFeedIngredientsBinding
import com.maxfin.filto.ui.floatDp

//Как поступить здесь
@BindingAdapter("formatIngredients")
fun LinearLayout.formatIngredients(ingredients: Map<String, String>?) {
    this.removeAllViews()
    if (ingredients != null) {
        val inflater = LayoutInflater.from(this.context)

        for (ingredient in ingredients) {
            if (ingredient.key.isNotEmpty()){
                val achievementView = ViewFeedIngredientsBinding.inflate(inflater, this, true)

                achievementView.text.text = "${ingredient.key} - ${ingredient.value}"
            }
        }
    }
}

@BindingAdapter("setBlur")
fun ImageView.setBlur(isBlur: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (isBlur){
            this.setRenderEffect(
                RenderEffect.createBlurEffect(
                    50.floatDp, 50.floatDp, Shader.TileMode.CLAMP
                )
            )
        }else{
            this.setRenderEffect(null)
        }
    }else{
        if (isBlur){
            this.alpha = 0.3F
        }else{
            this.alpha = 1F
        }


    }
}