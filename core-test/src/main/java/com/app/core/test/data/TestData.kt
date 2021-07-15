package com.app.core.test.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TestData(@Json(name = "name") val name: String)
