plugins {
  id("java-library")
  id("kotlin")
  id("jacoco")
  id("plugins.jacoco-report")
  kotlin("kapt")
}

dependencies {

  implementation(Libraries.kotlinJDK)

  implementation(Libraries.javaInject)
  implementation(Libraries.rxJava)

  implementation(Libraries.moshi)
  kapt(AnnotationProcessors.moshiCodegen)

  Libraries.suiteTest.forEach { testImplementation(it) }
}

afterEvaluate {
  val function = extra.get("generateCheckCoverageTasks") as (File, String, Coverage, List<String>, List<String>) -> Unit
  function.invoke(
    buildDir,
    "test",
    Coverage(
      instructions = 64.41,
      lines = 67.79,
      complexity = 44.64,
      methods = 42.42,
      classes = 50.00
    ),
    emptyList(),
    listOf("**/base/data/**", "**/base/Either*", "**/base/utils/Tuples*")
  )
}
