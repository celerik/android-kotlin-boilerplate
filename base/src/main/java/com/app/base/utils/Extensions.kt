package com.app.base.utils

import io.reactivex.rxjava3.disposables.Disposable
import java.lang.Enum.valueOf

fun Disposable.discard() {
  Unit
}

fun Boolean.toInt() = if (this) 1 else 0

fun Double?.isGreaterThanOrEqualTo(value: Double) = this != null && this >= value

inline fun <reified T : Enum<T>> safeValueOf(type: String?): T? {
  return try {
    type?.let { valueOf(T::class.java, it.replace("-", "_")) }
  } catch (e: IllegalArgumentException) {
    null
  }
}

inline fun <T : Any> multiLet(vararg elements: T?, closure: (List<T>) -> Unit): Unit? {
  return if (elements.all { it != null }) {
    closure(elements.filterNotNull())
  } else {
    null
  }
}
