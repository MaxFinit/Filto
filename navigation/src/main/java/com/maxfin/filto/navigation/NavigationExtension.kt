package com.maxfin.filto.navigation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

 const val NavigationTag = "NAVIGATION"

fun Fragment.saveNavigate(destinationId: Int) {
    try {
        findNavController().navigate(resId = destinationId)
    } catch (ex: Exception) {
        Log.e(NavigationTag, ex.stackTraceToString())
    }
}


fun Fragment.saveNavigate(navDirections: NavDirections) {
    try {
        findNavController().navigate(directions = navDirections)
    } catch (ex: Exception) {
        Log.e(NavigationTag, ex.stackTraceToString())
    }
}