package com.celerik.app.di.modules

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.app.core.interfaces.AppResources
import com.celerik.app.data.CelerikResources
import com.celerik.app.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseModule {
  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  abstract fun bindContext(celerikApp: Application): Context

  @Binds
  abstract fun bindResources(celerikResources: CelerikResources): AppResources
}
