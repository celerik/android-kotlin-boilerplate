package com.celerik.app

import com.app.base.interfaces.Logger
import timber.log.Timber
import javax.inject.Inject

class CelerikLogger @Inject constructor(tree: Timber.Tree) : Logger {

  init {
    Timber.plant(tree)
  }

  override fun v(message: String, throwable: Throwable?) {
    Timber.v(throwable, message)
  }

  override fun d(message: String, throwable: Throwable?) {
    Timber.d(throwable, message)
  }

  override fun i(message: String, throwable: Throwable?) {
    Timber.i(throwable, message)
  }

  override fun w(message: String, throwable: Throwable?) {
    Timber.w(throwable, message)
  }

  override fun e(message: String, throwable: Throwable?) {
    Timber.e(throwable, message)
  }
}
