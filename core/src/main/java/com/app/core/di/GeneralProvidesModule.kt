package com.app.core.di

import com.app.core.data.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GeneralProvidesModule {
  @Provides
  @Singleton
  fun providesSharedPreferencesHelper(): SharedPreferencesHelper {
    return SharedPreferencesHelper()
  }
}
