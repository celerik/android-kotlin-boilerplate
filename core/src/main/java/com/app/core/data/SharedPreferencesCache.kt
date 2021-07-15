package com.app.core.data

import android.content.Context
import android.content.SharedPreferences
import com.app.base.interfaces.Cache

class SharedPreferencesCache(name: String, context: Context) : Cache {

  private var sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
  private val editor: SharedPreferences.Editor

  init {
    editor = sharedPreferences.edit()
  }

  override fun saveString(key: String, value: String) {
    editor.putString(key, value)
    editor.commit()
  }

  override fun readString(key: String): String? {
    return sharedPreferences.getString(key, null)
  }

  override fun removeValue(key: String) {
    editor.remove(key)
    editor.apply()
  }

  override fun containsValues(): Boolean {
    return sharedPreferences.all.isNotEmpty()
  }

  override fun clearAll() {
    editor.clear()
    editor.apply()
  }
}
