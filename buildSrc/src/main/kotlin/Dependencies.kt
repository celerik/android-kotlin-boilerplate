object Versions {
  const val kotlin = "1.4.32"
  const val gradle = "4.2.2"
  const val jvmTarget = "1.8"

  const val gradleKlint = "10.0.0"
  const val androidJUnit5 = "1.7.1.1"
  const val jacoco = "0.8.7"

  //AndroidX
  const val appCompat = "1.2.0"
  const val core = "1.3.2"
  const val jUnitExtKtx = "1.1.2"
  const val testCoreKtx = "1.3.0"

  const val material = "1.3.0"

  const val constraintLayout = "2.0.4"
  const val espressoCore = "3.3.0"
  const val androidXRunner = "1.3.0"
  const val androidXRules = "1.3.0"
  const val fragmentKtx = "1.3.3"
  const val activityKtx = "1.2.2"
  const val recyclerView = "1.2.0"
  const val desugarJdkLibs = "1.1.5"

  const val dagger = "2.35"

  const val retrofit = "2.9.0"

  const val rxJava = "3.0.12"
  const val rxAndroid = "3.0.0"

  const val moshi = "1.12.0"
  const val multidex = "2.0.1"

  const val glide = "4.12.0"

  const val mockk = "1.11.0"

  const val okHttpBoM = "4.9.1"

  const val lifeCycle = "2.3.1"

  const val leakCanary = "2.7"
  const val timber = "4.7.1"
  const val sentry = "4.3.0"

  const val jUnit5 = "5.7.1"

  const val barista = "3.9.0"
  const val orchestrator = "1.3.0"

  const val okReplay = "1.6.0"
}

object Api {
  const val compileSDK = 30
  const val minSDK = 21
  const val targetSDK = 30
}

object Libraries {
  const val kotlinJDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
  const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
  const val androidXCore = "androidx.core:core-ktx:${Versions.core}"
  const val material = "com.google.android.material:material:${Versions.material}"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

  const val preferences = "androidx.preference:preference-ktx:1.1.1"
  const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
  const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
  const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
  const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions.desugarJdkLibs}"

  const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
  const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
  const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"

  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
  const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"

  const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
  const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"

  const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

  const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

  const val jUnitExtKtx = "androidx.test.ext:junit-ktx:${Versions.jUnitExtKtx}"
  const val testCoreKtx = "androidx.test:core-ktx:${Versions.testCoreKtx}"
  const val androidXRunner = "androidx.test:runner:${Versions.androidXRunner}"
  const val androidXRules = "androidx.test:rules:${Versions.androidXRules}"
  const val barista = "com.schibsted.spain:barista:${Versions.barista}"
  const val orchestrator = "androidx.test:orchestrator:${Versions.orchestrator}"
  const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

  const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
  const val lifeCycleProcess = "androidx.lifecycle:lifecycle-process:${Versions.lifeCycle}"
  const val lifeCycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle}"

  const val mockk = "io.mockk:mockk:${Versions.mockk}"
  const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
  const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

  const val kotlinTestJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

  const val okHttpBoM = "com.squareup.okhttp3:okhttp-bom:${Versions.okHttpBoM}"
  const val okHttp = "com.squareup.okhttp3:okhttp"
  const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor"
  const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttpBoM}"

  const val jUnit5 = "org.junit.jupiter:junit-jupiter:${Versions.jUnit5}"
  const val jUnit5Engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jUnit5}"

  val suiteTest = arrayOf(mockk, jUnit5, jUnitExtKtx, testCoreKtx, kotlinTest, kotlinReflect, kotlinTestJunit)

  const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
  const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

  const val sentry = "io.sentry:sentry-android:${Versions.sentry}"

  const val okReplay = "com.airbnb.okreplay:okreplay:${Versions.okReplay}"
  const val okReplayNoop = "com.airbnb.okreplay:noop:${Versions.okReplay}"
  const val okReplayEspresso = "com.airbnb.okreplay:espresso:${Versions.okReplay}"
}

object AnnotationProcessors {
  const val dagger = "com.google.dagger:dagger-compiler:${Versions.dagger}"
  const val daggerAndroid = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
  const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}
