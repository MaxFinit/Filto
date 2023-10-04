package com.maxfin.filto.ui

import android.content.res.Resources
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.floatDp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

val Int.floatDp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

fun View.fadeVisibility(visibility: Int, duration: Long = 300) {
    val transition: Transition = Fade()
    transition.duration = duration
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
    this.visibility = visibility

}

fun View.slideVisibility(visibility: Int, duration: Long = 300, direction: Int = Gravity.BOTTOM) {
    this.post {
        val transition: Transition = Slide(direction)
        transition.duration = duration
        transition.addTarget(this)
        TransitionManager.beginDelayedTransition(this.parent as ViewGroup, transition)
        this.visibility = visibility
    }
}