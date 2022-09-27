package com.example.nickelffoxassignments_sheenu.news.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
class ConnectionLiveData (private  val cm:ConnectivityManager):LiveData<Boolean>() {

    constructor(appContext:Application):this(appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    override fun onActive() {
        super.onActive()
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
        cm.registerNetworkCallback(networkRequest.build(),createNetworkCallback)

    }

    override fun onInactive() {
        super.onInactive()
        cm.unregisterNetworkCallback(createNetworkCallback)
    }

     private var createNetworkCallback= object : ConnectivityManager.NetworkCallback(){


        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)

        }


         override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
             super.onCapabilitiesChanged(network, networkCapabilities)
             val isInternet=networkCapabilities.hasCapability(NET_CAPABILITY_INTERNET)
             postValue(isInternet)
         }


         override fun onUnavailable() {
             super.onUnavailable()
             postValue(false)
         }

         override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }


}