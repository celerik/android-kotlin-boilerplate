package com.app.core.network

import com.app.base.data.HttpObject
import com.app.base.interfaces.Logger
import com.app.core.exceptions.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ServerInterceptor @Inject constructor(private val logger: Logger) : Interceptor {

  @Throws(ServerException::class, NoConnectionException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()
    try {
      val response = chain.proceed(request)
      val httpCode = response.code

      val method = request.method
      val endpoint = request.url.toString()

      if (httpCode in 400..500) {
        var responseBody: JSONObject? = null
        val serverException = try {
          responseBody = JSONObject(response.body?.string().orEmpty())
          val code = responseBody.optString("code")
          val message = responseBody.optString("message")
          ServerException(code, message, httpCode)
        } catch (e: Exception) {
          ServerException("UNKNOWN", "GENERIC ERROR", httpCode, "$request $response")
        }

        val infoRequest = HttpObject(
          method, endpoint, responseBody.toString(), httpCode
        )

        logger.http(
          endpoint,
          method,
          infoRequest.toString(),
          response = response.toString(),
          statusCode = httpCode
        )

        logger.e("Server Exception ${serverException.message}", serverException)

        throw serverException
      } else {
        return response
      }
    } catch (serverException: ServerException) {
      throw serverException
    } catch (_: UnknownHostException) {
      throw NoConnectionException
    } catch (_: SocketTimeoutException) {
      throw NoConnectionException
    } catch (_: NoConnectionException) {
      throw NoConnectionException
    } catch (_: IOException) {
      throw NoConnectionException
    } catch (e: Exception) {
      throw ServerException("Network Issue", e.message.orEmpty())
    }
  }
}

data class ServerException(
  val code: String,
  override val message: String = "",
  val httpCode: Int = 0,
  val extra: String = ""
) : IOException(message)
