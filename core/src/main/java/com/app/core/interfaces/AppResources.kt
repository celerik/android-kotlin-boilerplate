package com.app.core.interfaces

interface AppResources {
  fun getString(resId: Int): String
  fun getString(resId: Int, vararg others: String): String
  fun getColor(resId: Int): Int
  fun parseColor(color: String): Int
}
