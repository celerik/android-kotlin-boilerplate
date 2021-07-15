plugins {
  id("com.android.library")
  id("de.mannodermaus.android-junit5")
  kotlin("android")
  kotlin("kapt")
}

android {
  compileSdkVersion(Api.compileSDK)
  defaultConfig {
    minSdkVersion(Api.minSDK)
    targetSdkVersion(Api.targetSDK)
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  lintOptions {
    isAbortOnError = false
  }

  buildFeatures {
    dataBinding = true
    viewBinding = true
  }
}

dependencies {
  implementation(project(":base"))

  implementation(Libraries.multidex)

  implementation(Libraries.kotlinJDK)
  implementation(Libraries.appcompat)
  implementation(Libraries.androidXCore)
  implementation(Libraries.constraintLayout)
  implementation(Libraries.glide)
  implementation(Libraries.recyclerView)

  implementation(Libraries.retrofit)
  implementation(Libraries.retrofitMoshi)
  implementation(Libraries.retrofitRxJava)
  implementation(platform(Libraries.okHttpBoM))
  implementation(Libraries.okHttpInterceptor)
  implementation(Libraries.moshi)
  kapt(AnnotationProcessors.moshiCodegen)

  implementation(Libraries.dagger)
  kapt(AnnotationProcessors.dagger)

  Libraries.suiteTest.forEach { testImplementation(it) }
  testImplementation(project(":core-test"))

  androidTestImplementation(Libraries.jUnitExtKtx)
  androidTestImplementation(Libraries.espressoCore)
}
