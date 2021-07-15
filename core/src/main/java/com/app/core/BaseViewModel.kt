package com.app.core

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(), LifecycleObserver {

  protected val disposables = CompositeDisposable()

  override fun onCleared() {
    disposables.clear()
    super.onCleared()
  }
}
