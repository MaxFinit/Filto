package com.maxfin.filto.ui

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest

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