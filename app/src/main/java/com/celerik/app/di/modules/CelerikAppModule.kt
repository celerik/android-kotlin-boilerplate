package com.celerik.app.di.modules

import android.content.ContentResolver
import android.content.Context
import com.app.base.interfaces.Logger
import com.celerik.app.viewModels.CelerikAppViewModel
import com.app.core.interfaces.AppResources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CelerikAppModule {
  @Provides
  fun postCelerikAppViewModel(
    logger: Logger,
    appResources: AppResources,
  ): CelerikAppViewModel {
    return CelerikAppViewModel(
      logger,
      appResources,
    )
  }

  @Provides
  @Singleton
  fun providesContentResolver(context: Context): ContentResolver {
    return context.contentResolver
  }
}
