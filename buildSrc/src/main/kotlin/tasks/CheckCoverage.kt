package tasks

import groovy.util.Node
import groovy.util.XmlParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File
import org.gradle.api.GradleException
import Coverage
import java.math.BigDecimal
import java.math.RoundingMode

private const val INSTRUCTION = "INSTRUCTION"
private const val LINE = "LINE"
private const val COMPLEXITY = "COMPLEXITY"
private const val METHOD = "METHOD"
private const val CLASS = "CLASS"

open class CheckCoverage : DefaultTask() {
  @Input
  lateinit var coverageFilePath: String

  @Input
  lateinit var coverage: Coverage

  @ExperimentalStdlibApi
  @TaskAction
  fun sendMessage() {

    val parser = XmlParser()
    parser.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false)
    parser.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false)
    val file = File(coverageFilePath)
    if (file.exists()) {
      val formatter = parser.parse(file)

      val properties = mutableMapOf(
        INSTRUCTION to Pair(0.0, 0.0),
        LINE to Pair(0.0, 0.0),
        COMPLEXITY to Pair(0.0, 0.0),
        METHOD to Pair(0.0, 0.0),
        CLASS to Pair(0.0, 0.0)
      )

      formatter.children().forEach { node ->
        properties.forEach {
          properties[it.key] =
            properties[it.key]?.plus(getSumForType(node as Node, it.key)) ?: Pair(0.0, 0.0)
        }
      }

      val propertiesCovered = Coverage(
        instructions = getCalculatedValue(properties[INSTRUCTION]),
        lines = getCalculatedValue(properties[LINE]),
        complexity = getCalculatedValue(properties[COMPLEXITY]),
        methods = getCalculatedValue(properties[METHOD]),
        classes = getCalculatedValue(properties[CLASS])
      )

      val coveragePassed = mutableListOf<String>()
      val coverageFailed = mutableListOf<String>()

      val instructionsMessage =
        "$INSTRUCTION --> ${getRoundValue(propertiesCovered.instructions)}% expected: ${coverage.instructions}%"
      if (propertiesCovered.instructions.asBigDecimal() < coverage.instructions.asBigDecimal()) {
        coverageFailed.add(instructionsMessage)
      } else {
        coveragePassed.add(instructionsMessage)
      }

      val linesMessage =
        "$LINE ---------> ${getRoundValue(propertiesCovered.lines)}% expected: ${coverage.lines}%"
      if (propertiesCovered.lines.asBigDecimal() < coverage.lines.asBigDecimal()) {
        coverageFailed.add(linesMessage)
      } else {
        coveragePassed.add(linesMessage)
      }

      val complexityMessage =
        "$COMPLEXITY ----> ${getRoundValue(propertiesCovered.complexity)}% expected: ${coverage.complexity}%"
      if (propertiesCovered.complexity.asBigDecimal() < coverage.complexity.asBigDecimal()) {
        coverageFailed.add(complexityMessage)
      } else {
        coveragePassed.add(complexityMessage)
      }

      val methodsMessage =
        "$METHOD -------> ${getRoundValue(propertiesCovered.methods)}% expected: ${coverage.methods}%"
      if (propertiesCovered.methods.asBigDecimal() < coverage.methods.asBigDecimal()) {
        coverageFailed.add(methodsMessage)
      } else {
        coveragePassed.add(methodsMessage)
      }

      val classesMessage =
        "$CLASS -------> ${getRoundValue(propertiesCovered.classes)}% expected: ${coverage.classes}%"
      if (propertiesCovered.classes.asBigDecimal() < coverage.classes.asBigDecimal()) {
        coverageFailed.add(classesMessage)
      } else {
        coveragePassed.add(classesMessage)
      }

      if (coverageFailed.isEmpty()) {
        logger.quiet("************ COVERAGE PASSED ************")
        coveragePassed.forEach {
          logger.quiet(it)
        }
        logger.quiet("*****************************************")
      } else {
        val exceptionMessage = "************ COVERAGE FAILED ************\n"
          .plus(coverageFailed.joinToString(separator = "\n"))
          .plus("\n*****************************************")
        throw GradleException(exceptionMessage)
      }
    } else {
      logger.quiet("No coverage file found")
    }
  }
}

fun getRoundValue(value: Double): String {
  return String.format("%.2f", value)
}

fun getCalculatedValue(data: Pair<Double, Double>?): Double {
  return if (data != null) {
    (data.first / (data.first + data.second)) * 100.0
  } else {
    0.0
  }
}

fun Pair<Double, Double>.plus(pair: Pair<Double, Double>): Pair<Double, Double> {
  return Pair(first + pair.first, second + pair.second)
}

fun getSumForType(node: Node, type: String): Pair<Double, Double> {
  var resultCovered = 0.0
  var resultMissed = 0.0
  node.children().forEach {
    val internalNode = (it as Node)
    if (internalNode.attribute("type") == type) {
      resultCovered = resultCovered.plus(it.attribute("covered").toString().toDouble())
      resultMissed = resultMissed.plus(it.attribute("missed").toString().toDouble())
    }

    if (internalNode.children().isNotEmpty()) {
      val result = getSumForType(internalNode, type)
      resultCovered = resultCovered.plus(result.first)
      resultMissed = resultMissed.plus(result.second)
    }
  }
  return Pair(resultCovered, resultMissed)
}

fun Double.asBigDecimal(): BigDecimal {
  return BigDecimal(this).setScale(2, RoundingMode.HALF_UP)
}
