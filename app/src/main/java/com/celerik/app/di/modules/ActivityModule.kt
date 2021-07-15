package com.celerik.app.di.modules

import com.celerik.app.MainActivity
import com.celerik.app.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

  @ContributesAndroidInjector(modules = [SplashModule::class])
  abstract fun bindSplashActivity(): SplashActivity

  @ContributesAndroidInjector(modules = [MainBindsModule::class])
  abstract fun bindMainActivity(): MainActivity
}
