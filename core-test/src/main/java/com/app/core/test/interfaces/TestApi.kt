package com.app.core.test.interfaces

import com.app.core.test.data.TestData
import retrofit2.Call
import retrofit2.http.GET

interface TestApi {
  @GET("/test")
  fun test(): Call<TestData>
}
