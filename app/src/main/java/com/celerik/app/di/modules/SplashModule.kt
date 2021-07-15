package com.celerik.app.di.modules

import androidx.lifecycle.ViewModel
import com.celerik.app.viewModels.SplashViewModel
import com.app.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [AppUseCasesModule::class])
abstract class SplashModule {
  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel::class)
  abstract fun bindsSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}
