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

  buildFeatures {
    dataBinding = true
    viewBinding = true
  }
}

dependencies {
  implementation(Libraries.multidex)

  implementation(Libraries.kotlinJDK)
  implementation(Libraries.appcompat)
  implementation(Libraries.activityKtx)
  implementation(Libraries.androidXCore)
  implementation(Libraries.lifeCycleCommonJava8)
  implementation(Libraries.constraintLayout)
  implementation(Libraries.material)

  Libraries.suiteTest.forEach { testImplementation(it) }

  androidTestImplementation(Libraries.jUnitExtKtx)
  androidTestImplementation(Libraries.espressoCore)
}
