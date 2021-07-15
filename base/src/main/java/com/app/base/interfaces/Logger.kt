package com.app.base.interfaces

interface Logger {
  fun v(message: String, throwable: Throwable? = null)
  fun d(message: String, throwable: Throwable? = null)
  fun i(message: String, throwable: Throwable? = null)
  fun w(message: String, throwable: Throwable? = null)
  fun e(message: String, throwable: Throwable? = null)
}
