package com.app.base.data

import java.io.Serializable

data class DateObject(
  val date: Int,
  val timeZone: String
) : Serializable
