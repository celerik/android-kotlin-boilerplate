package com.app.core.data

import com.app.base.interfaces.Cache
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class SharedPreferencesHelper {

  inline fun <reified T> getObject(key: String, cache: Cache): T? {
    val moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<T> = moshi.adapter(T::class.java)
    return adapter.fromJson(cache.readString(key).orEmpty())
  }

  inline fun <reified T> saveObject(obj: T, key: String, cache: Cache) {
    val moshi = Moshi.Builder().build()
    val jsonAdapter: JsonAdapter<T> = moshi.adapter(T::class.java)
    val json: String = jsonAdapter.toJson(obj)
    cache.saveString(key, json)
  }
}
