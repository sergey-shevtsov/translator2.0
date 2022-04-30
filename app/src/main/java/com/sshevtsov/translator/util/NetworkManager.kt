package com.sshevtsov.translator.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkManager(context: Context) {
  private val _connectionStatus = MutableStateFlow(ConnectionStatus.Lost)
  val connectionStatus: Flow<ConnectionStatus> = _connectionStatus
  fun getConnectionStatus(): ConnectionStatus = _connectionStatus.value

  private val connectivityManager =
    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

  private val networkCallback = object : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
      _connectionStatus.value = ConnectionStatus.Connected
    }

    override fun onLost(network: Network) {
      _connectionStatus.value = ConnectionStatus.Lost
    }
  }

  private val networkRequest = NetworkRequest.Builder()
    .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
    .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
    .build()

  init {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      connectivityManager.registerDefaultNetworkCallback(networkCallback)
    } else {
      connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
  }
}

enum class ConnectionStatus {
  Connected, Lost
}