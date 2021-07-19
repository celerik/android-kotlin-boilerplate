package com.celerik.app.di.modules

import com.app.core.interfaces.IntentResolver
import com.celerik.app.data.ResolveIntentImpl
import dagger.Module
import dagger.Provides

@Module
class IntentsModule {

  @Provides
  fun providesResolveIntentImplementation(): IntentResolver {
    return ResolveIntentImpl()
  }
}
