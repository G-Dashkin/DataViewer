package com.perfomax.dataviewer.ui.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.isConnected(): Boolean {

    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val cap = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            when {
                cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        else -> {
            val activityNetwork = connectivityManager.activeNetworkInfo ?: return false
            return when (activityNetwork.type) {
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_VPN -> true
                else -> false
            }
        }
    }

}