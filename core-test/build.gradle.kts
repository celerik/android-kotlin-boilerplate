plugins {
  id("com.android.library")
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
}

dependencies {
  implementation(Libraries.multidex)
  implementation(Libraries.jUnit5)
  implementation(Libraries.kotlinJDK)
  implementation(Libraries.appcompat)
  implementation(Libraries.androidXCore)

  implementation(Libraries.rxJava)
  implementation(Libraries.rxAndroid)

  implementation(Libraries.moshi)
  kapt(AnnotationProcessors.moshiCodegen)

  implementation(Libraries.retrofit)
  implementation(Libraries.retrofitMoshi)
  implementation(Libraries.retrofitRxJava)

  androidTestImplementation(Libraries.jUnitExtKtx)
  androidTestImplementation(Libraries.espressoCore)
}
