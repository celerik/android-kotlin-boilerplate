package com.celerik.app.di.modules

import com.celerik.app.data.ResolveIntentImpl
import com.app.core.interfaces.IntentResolver
import dagger.Module
import dagger.Provides

@Module
class IntentsModule {

  @Provides
  fun providesResolveIntentImplementation(): IntentResolver {
    return ResolveIntentImpl()
  }
}
