package com.app.base.data

data class HttpObject(
  val method: String,
  val request: String,
  val response: String,
  val httpCode: Int
)
