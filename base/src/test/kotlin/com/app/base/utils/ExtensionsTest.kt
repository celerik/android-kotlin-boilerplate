package com.app.base.utils

import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.disposables.Disposable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@ExtendWith(MockKExtension::class)
class ExtensionsTest {

  companion object {
    @JvmStatic
    private fun `Should return an Int value when boolean toInt is invoked`() = Stream.of(
      Arguments.of(true, 1),
      Arguments.of(false, 0)
    )

    @JvmStatic
    private fun `Should return Unit or null when multiLet is either activated or not respectively`() = Stream.of(
      Arguments.of(null, null),
      Arguments.of("value3", Unit)
    )

    @JvmStatic
    private fun `Should return enum element when a string one is sent`() = Stream.of(
      Arguments.of(null, null),
      Arguments.of("CAR", Vehicles.CAR)
    )

    @JvmStatic
    private fun `Should return true when value1 is greater or equal than value2`() = Stream.of(
      Arguments.of(0, 1, false),
      Arguments.of(5, 3, true),
      Arguments.of(5, 5, true)
    )
  }

  @Test
  fun `Should return Unit when disposable discard is invoked`() {
    // given
    val testDisposable = Disposable.fromAction { }

    // when
    val returnedValue = testDisposable.discard()

    // then
    assert(returnedValue == Unit)
  }

  @ParameterizedTest
  @MethodSource
  fun `Should return enum element when a string one is sent`(enumElementInString: String?, expectedValue: Vehicles?) {
    // when
    val enumElement = safeValueOf<Vehicles>(enumElementInString)

    // then
    assert(enumElement == expectedValue)
  }

  @ParameterizedTest
  @MethodSource
  fun `Should return an Int value when boolean toInt is invoked`(booleanValue: Boolean, expectedIntValue: Int) {
    // when
    val intValue = booleanValue.toInt()

    // then
    assert(intValue == expectedIntValue)
  }

  @ParameterizedTest
  @MethodSource
  fun `Should return Unit or null when multiLet is either activated or not respectively`(val3: String?, expectedResult: Unit?) {
    // given
    val val1 = "asd"
    val val2 = 512

    // when
    val multiLetResult = multiLet(val1, val2, val3) {
      // no-op by default
    }

    // then
    assert(multiLetResult == expectedResult)
  }

  @ParameterizedTest
  @MethodSource
  fun `Should return true when value1 is greater or equal than value2`(value1: Double, value2: Double, expectedResult: Boolean) {
    // when
    val result = value1.isGreaterThanOrEqualTo(value2)

    // then
    assert(result == expectedResult)
  }

  enum class Vehicles {
    CAR
  }
}
