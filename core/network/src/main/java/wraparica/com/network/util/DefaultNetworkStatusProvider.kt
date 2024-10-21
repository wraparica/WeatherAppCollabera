package wraparica.com.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import javax.inject.Inject

interface NetworkStatusProvider {
    fun provideNetworkConnectionStatus(): NetworkConnectionStatus
}

class DefaultNetworkStatusProvider @Inject constructor(context: Context) :
    NetworkStatusProvider {

    private val connectivityManager: ConnectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun provideNetworkConnectionStatus(): NetworkConnectionStatus = getNetworkStatus()

    private fun getNetworkStatus(): NetworkConnectionStatus {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // this part of code is to be executed on devices running on api level less than 23

            val networkInfo =
                connectivityManager.activeNetworkInfo
                    ?: return NetworkConnectionStatus.NotConnectedToInternet // Deprecated in 29

            with(networkInfo) {
                // first we check if device is connected to internet
                if (!isConnected) {
                    return NetworkConnectionStatus.NotConnectedToInternet
                }

                return when (type) {
                    // here we check if the device is connected to a wifi network
                    ConnectivityManager.TYPE_WIFI -> {
                        NetworkConnectionStatus.ConnectedToWifi
                    }
                    // here we check if the device is connected to mobile data
                    ConnectivityManager.TYPE_MOBILE -> {
                        NetworkConnectionStatus.ConnectedToMobileData
                    }
                    // finally we provide status 'Other' as there could be more connection types (ex: ethernet)
                    ConnectivityManager.TYPE_ETHERNET -> {
                        NetworkConnectionStatus.Other
                    }
                    else -> {
                        NetworkConnectionStatus.NotConnectedToInternet
                    }
                }
            }
        } else {
            // this part of code is to be executed on devices running on api level 23 or more

            val activeNetwork = connectivityManager.activeNetwork
                ?: return NetworkConnectionStatus.NotConnectedToInternet
            val networkInfo =
                connectivityManager.getNetworkCapabilities(activeNetwork)
                    ?: return NetworkConnectionStatus.NotConnectedToInternet

            with(networkInfo) {

                return when {
                    // here we check if the device is connected to a wifi network
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        NetworkConnectionStatus.ConnectedToWifi
                    }
                    // here we check if the device is connected to mobile data
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        NetworkConnectionStatus.ConnectedToMobileData
                    }
                    // finally we provide status 'Other' as there could be more connection types (ex: ethernet)
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        NetworkConnectionStatus.Other
                    }
                    else -> {
                        NetworkConnectionStatus.NotConnectedToInternet
                    }
                }
            }

        }
    }
}

sealed class NetworkConnectionStatus {

    object ConnectedToMobileData : NetworkConnectionStatus()

    object ConnectedToWifi : NetworkConnectionStatus()

    object NotConnectedToInternet : NetworkConnectionStatus()

    object Other : NetworkConnectionStatus()
}