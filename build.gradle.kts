import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath("com.android.tools.build:gradle:${Versions.gradle}")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    classpath("org.jlleitschuh.gradle:ktlint-gradle:${Versions.gradleKlint}")
    classpath("de.mannodermaus.gradle.plugins:android-junit5:${Versions.androidJUnit5}")
    classpath("org.jacoco:org.jacoco.core:${Versions.jacoco}")
  }
}

plugins {
  id("org.sonarqube") version "3.1.1"
}

allprojects {
  apply(plugin = "org.jlleitschuh.gradle.ktlint")

  repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
    jcenter {
      content {
        includeGroup("com.schibsted.spain") // https://github.com/AdevintaSpain/Barista/issues/382
      }
    }
  }

  plugins.withType(JavaLibraryPlugin::class) {
    tasks.getByName("test", Test::class) {
      useJUnitPlatform()
      testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events("passed", "failed", "skipped", "standardOut", "standardError")
      }
    }
  }

  sonarqube {
    properties {
      setProperty("sonar.sources", "src")
    }
  }
}

subprojects {
  tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = Versions.jvmTarget
  }

  apply(plugin = "jacoco")
  apply(plugin = "plugins.jacoco-report")

  plugins.withType<JacocoPlugin> {
    the<JacocoPluginExtension>().apply {
      toolVersion = Versions.jacoco
      reportsDir = file("$buildDir/jacoco/reports")
    }
  }
}

tasks.register("clean", Delete::class.java) {
  delete(rootProject.buildDir)
}

sonarqube {
  properties {
    setProperty("sonar.projectKey", "projectKey")
    setProperty("sonar.organization", "organization")
    setProperty("sonar.host.url", "https://sonarcloud.io")
    setProperty("sonar.sourceEncoding", "UTF-8")
  }
}
