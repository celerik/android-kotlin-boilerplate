package com.app.base.interfaces

interface Logger {
  fun v(message: String, throwable: Throwable? = null)
  fun d(message: String, throwable: Throwable? = null)
  fun i(message: String, throwable: Throwable? = null)
  fun w(message: String, throwable: Throwable? = null)
  fun e(message: String, throwable: Throwable? = null)
  fun http(url: String, method: String, request: String? = null, response: String? = null, statusCode: Int? = null)
}
