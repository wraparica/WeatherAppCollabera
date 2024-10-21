package wraparica.com.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import wraparica.com.network.util.NetworkConnectionStatus
import wraparica.com.network.util.NetworkStatusProvider
import java.io.IOException
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(
    private val networkStatusProvider: NetworkStatusProvider
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        when (networkStatusProvider.provideNetworkConnectionStatus()) {
            NetworkConnectionStatus.NotConnectedToInternet -> throw NoInternet
            else -> return chain.proceed(chain.request())
        }
    }
}

object NoInternet : IOException()