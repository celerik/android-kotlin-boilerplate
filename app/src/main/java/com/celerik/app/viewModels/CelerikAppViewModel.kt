package com.celerik.app.viewModels

import androidx.lifecycle.LifecycleObserver
import com.app.base.interfaces.Logger
import com.app.core.BaseViewModel
import com.app.core.interfaces.AppResources
import javax.inject.Inject

class CelerikAppViewModel @Inject constructor(
  private val logger: Logger,
  private val appResources: AppResources,
) : BaseViewModel(), LifecycleObserver
