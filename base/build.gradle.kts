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
      instructions = 8.24,
      lines = 12.31,
      complexity = 12.82,
      methods = 9.68,
      classes = 9.09
    ),
    emptyList(),
    listOf("**/base/data/**", "**/base/Either*", "**/base/utils/Tuples*")
  )
}
