package com.celerik.app.di.modules

import androidx.lifecycle.ViewModel
import com.celerik.app.viewModels.MainViewModel
import com.app.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainBindsModule {
  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel
}
