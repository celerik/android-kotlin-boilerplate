package com.app.base.interfaces

interface Cache {
  fun saveString(key: String, value: String)

  fun readString(key: String): String?

  fun removeValue(key: String)

  fun containsValues(): Boolean

  fun clearAll()
}
