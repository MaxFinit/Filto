package com.maxfin.filto.ui

import android.content.res.Resources

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.floatDp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

val Int.floatDp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)