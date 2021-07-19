package com.celerik.app.di.modules

import com.app.base.interfaces.SingleUseCase
import com.app.core.qualifiers.VerifyInternet
import com.celerik.app.useCases.VerifyInternetConnectivityUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppUseCasesModule {

  @Binds
  @VerifyInternet
  abstract fun bindVerifyInternetConnectivityUseCase(useCase: VerifyInternetConnectivityUseCase): SingleUseCase<Unit, Boolean>
}
