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
    val tree = CelerikDebugTree() // The logger could be changed according to current environment
    return CelerikLogger(tree)
  }
}
