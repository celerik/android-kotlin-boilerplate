package com.celerik.app.di.modules

import com.app.base.interfaces.Logger
import com.celerik.app.CelerikDebugTree
import com.celerik.app.CelerikLogger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object LoggerModule {
  @Provides
  @Singleton
  fun providesLoggerImplementation(): Logger {
    // TODO: change logger tree if is BuildConfig.DEBUG or release
    val tree = CelerikDebugTree()
    return CelerikLogger(tree)
  }
}
