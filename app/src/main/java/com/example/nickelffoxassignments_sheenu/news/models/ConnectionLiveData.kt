package com.example.nickelffoxassignments_sheenu.news.models

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConnectionLiveData @Inject constructor(@ApplicationContext var context:Context):LiveData<Boolean>() {

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    private val cm=context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager


    override fun onActive() {
        super.onActive()
        networkCallback = createNetworkCallback()
        val networkRequest = NetworkRequest.Builder()
            .build()
        cm.registerNetworkCallback(networkRequest, networkCallback)

    }

    override fun onInactive() {
        super.onInactive()
        cm.unregisterNetworkCallback(networkCallback)
    }

    private fun createNetworkCallback()= object : ConnectivityManager.NetworkCallback(){


        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)


        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }


}