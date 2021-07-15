package com.app.core.test.utils

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InstantExecutorExtension : BeforeAllCallback, AfterAllCallback {

  @SuppressLint("RestrictedApi")
  override fun beforeAll(context: ExtensionContext?) {
    ArchTaskExecutor.getInstance()
      .setDelegate(object : TaskExecutor() {
        override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

        override fun postToMainThread(runnable: Runnable) = runnable.run()

        override fun isMainThread(): Boolean = true
      })
  }

  @SuppressLint("RestrictedApi")
  override fun afterAll(context: ExtensionContext?) {
    ArchTaskExecutor.getInstance().setDelegate(null)
  }
}
