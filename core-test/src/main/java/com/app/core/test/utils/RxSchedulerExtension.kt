package com.app.core.test.utils

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class RxSchedulerExtension : AfterEachCallback, BeforeEachCallback {

  private val scheduler by lazy { Schedulers.trampoline() }

  override fun beforeEach(context: ExtensionContext) {
    RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
    RxJavaPlugins.setIoSchedulerHandler { scheduler }
  }

  override fun afterEach(context: ExtensionContext) {
    RxAndroidPlugins.reset()
    RxJavaPlugins.reset()
  }
}
