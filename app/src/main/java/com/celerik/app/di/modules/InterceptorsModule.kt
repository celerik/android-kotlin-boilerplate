package com.celerik.app.di.modules

import com.app.core.network.ServerInterceptor
import com.celerik.app.network.NetworkConnectivityInterceptor
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import okhttp3.Interceptor

@Module
abstract class InterceptorsModule {

  @Binds
  @IntoSet
  abstract fun bindsServerInterceptor(serverInterceptor: ServerInterceptor): Interceptor

  @Binds
  @IntoSet
  abstract fun bindsNetworkConnectivityInterceptor(networkConnectivityInterceptor: NetworkConnectivityInterceptor): Interceptor
}
