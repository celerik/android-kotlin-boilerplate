package com.celerik.components.utils

import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

inline fun <reified T : Serializable?> Fragment.getSerializableArgument(key: String, default: T? = null): T {
  val isNullable = null is T
  var value = arguments?.getSerializable(key)
  if (value == null || value !is T) {
    value = default
  }
  if (value == null && !isNullable) {
    throw Exception("Unable to get serializable $key from bundle argument")
  }
  return value as T
}

inline fun <reified T : Parcelable?> Fragment.getParcelableArgument(key: String, default: T? = null): T {
  val isNullable = null is T
  var value = arguments?.getParcelable<T>(key)
  if (value == null) {
    value = default
  }
  if (value == null && !isNullable) {
    throw Exception("Unable to get parcelable $key from bundle argument")
  }
  return value as T
}
