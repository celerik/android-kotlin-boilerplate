package com.celerik.app.data

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.app.core.interfaces.AppResources
import javax.inject.Inject

class CelerikResources @Inject constructor(private val context: Context) : AppResources {

  override fun getString(resId: Int): String {
    return context.getString(resId)
  }

  override fun getString(resId: Int, vararg others: String): String {
    return context.getString(resId, *others)
  }

  override fun getColor(resId: Int): Int {
    return ContextCompat.getColor(context, resId)
  }

  override fun parseColor(color: String): Int {
    return Color.parseColor(color)
  }
}
