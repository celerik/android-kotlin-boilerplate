package plugins

import Coverage
import tasks.CheckCoverage

extra.set(
  "generateCheckCoverageTasks",
  { buildDirectory: File, task: String, coverageData: Coverage, filesToInclude: List<String>, filesToExclude: List<String> ->

    task("checkCoverage", CheckCoverage::class) {
      dependsOn("jacocoAndroidTestReport")
      group = "verification"
      coverage = coverageData
      coverageFilePath =
        "${buildDirectory.absolutePath}/jacoco/reports/jacocoAndroidTestReport/jacocoAndroidTestReport.xml"
      doFirst {
        println("Code coverage file: ${buildDirectory.absolutePath}/reports/coverage/index.html")
      }
    }

    task("jacocoAndroidTestReport", JacocoReport::class) {
      dependsOn(task)
      group = "verification"
      description = "Code coverage report for Unit tests."
      sourceDirectories.setFrom(getSourceDirectoriesTree())
      classDirectories.setFrom(
        getClassDirectoriesTree(
          buildDirectory.absolutePath,
          filesToInclude, filesToExclude
        )
      )
      executionData.setFrom(getExecutionDataTree(buildDirectory.absolutePath, task))

      reports {
        xml.isEnabled = true
        csv.isEnabled = false
        html.isEnabled = true
        html.destination = File("${buildDirectory.absolutePath}/reports/coverage")
      }
    }
  })

fun getSourceDirectoriesTree(): List<String> {
  return listOf(
    "src/main/java"
  )
}

fun getExecutionDataTree(buildDir: String, executionTask: String): List<String> {
  return listOf(
    "$buildDir/jacoco/$executionTask.exec"
  )
}

fun getClassDirectoriesTree(
  buildDir: String,
  filesToInclude: List<String>,
  filesToExclude: List<String>
): ConfigurableFileTree {
  val fileTree = fileTree(buildDir)

  fileTree.include(
    "**/classes/**/main/**",
    "**/tmp/kotlin-classes/debug/**"
  )

  fileTree.include(filesToInclude)

  fileTree.exclude(
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "**/*Activity*.*",
    "**/*Fragment*.*",
    "**/*JsonAdapter*.*",
    "**/*_Factory.class",
    "**/*_MembersInjector.class",
    "**/di/**",
    "**/services/**",
    "**/BR.class",
    "**/DataBinderMapperImpl.class",
    "**/databinding/**",
    "**/bindings/**",
    "**/DataBindingInfo.class",
    "**/*\$Lambda$*.*", // Jacoco can not handle several "$" in class name.
    "**/*\$inlined$*.*" // Kotlin specific, Jacoco can not handle several "$" in class name.
  )

  fileTree.exclude(filesToExclude)
  return fileTree
}
