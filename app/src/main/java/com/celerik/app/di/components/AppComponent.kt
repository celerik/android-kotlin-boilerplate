package com.celerik.app.di.components

import android.app.Application
import com.app.core.CoreComponent
import com.celerik.app.CelerikApp
import com.celerik.app.di.modules.ActivityModule
import com.celerik.app.di.modules.BaseModule
import com.celerik.app.di.modules.FragmentModule
import com.celerik.app.di.modules.IntentsModule
import com.celerik.app.di.modules.LoggerModule
import com.celerik.app.di.modules.CelerikAppModule
import com.celerik.app.di.modules.CelerikUseCasesModule
import com.celerik.app.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    IntentsModule::class,
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    BaseModule::class,
    NetworkModule::class,
    ActivityModule::class,
    FragmentModule::class,
    LoggerModule::class,
    CelerikAppModule::class,
    CelerikUseCasesModule::class,
  ]
)
interface AppComponent : CoreComponent, AndroidInjector<CelerikApp> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}
