package com.celerik.app.network

import com.app.core.exceptions.NoConnectionException
import com.app.core.network.ServerException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkConnectivityInterceptor @Inject constructor() : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    try {
      val request = chain.request()
      return chain.proceed(request)
    } catch (serverException: ServerException) {
      throw serverException
    } catch (_: IOException) {
      throw NoConnectionException
    }
  }
}
