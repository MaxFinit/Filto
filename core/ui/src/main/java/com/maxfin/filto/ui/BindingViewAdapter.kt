package com.maxfin.filto.ui

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.google.android.material.button.MaterialButton

@BindingAdapter("isShow", requireAll = false)
fun View.isShow(isShow: Boolean?) {
    if (isShow == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

@BindingAdapter("isShowInvisible", requireAll = false)
fun View.isShowInvisible(isShow: Boolean?) {
    if (isShow == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

@BindingAdapter("isShowFade", requireAll = false)
fun View.isShowFade(isShow: Boolean?) {
    if (isShown == isShow){
        return
    }else{
        if (isShow == true) {
            this.fadeVisibility(View.VISIBLE)
        } else {
            this.fadeVisibility(View.GONE)
        }
    }
}

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(url: String?) {
    if (!url.isNullOrEmpty()) {
        this.load(url)
        {
            crossfade(true)
            listener(onError = { request: ImageRequest, error: ErrorResult ->
                Log.d("COIl", error.throwable.toString())
            })
        }


    }
}

@BindingAdapter("setBindingIconResource")
fun MaterialButton.setBindingIconResource(@DrawableRes icon: Int) {
   this.setIconResource(icon)
}