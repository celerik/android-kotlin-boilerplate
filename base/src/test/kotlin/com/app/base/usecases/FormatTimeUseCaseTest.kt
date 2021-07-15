package com.app.base.usecases

import com.app.base.data.DateObject
import com.app.base.interfaces.Logger
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Locale
import java.util.TimeZone
import java.util.stream.Stream
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class FormatTimeUseCaseTest {

  @MockK(relaxed = true)
  private lateinit var logger: Logger

  private val timeZone = TimeZone.getTimeZone("GMT-5")

  private lateinit var useCase: FormatTimeUseCase

  companion object {
    private val morningTime = DateObject(1595244561, "UTC")
    private val afternoonTime = DateObject(1595359991, "UTC")

    @JvmStatic
    private fun `Return correct formatted time when use case is executed`() = Stream.of(
      Arguments.of(Locale.ENGLISH, morningTime, "6:29 AM"),
      Arguments.of(Locale.ENGLISH, afternoonTime, "2:33 PM"),
      Arguments.of(Locale("pt", "BR"), morningTime, "06:29"),
      Arguments.of(Locale("pt", "BR"), afternoonTime, "14:33"),
      Arguments.of(Locale("es", "CO"), morningTime, "6:29 a. m."),
      Arguments.of(Locale("es", "CO"), afternoonTime, "2:33 p. m."),
      Arguments.of(Locale("es", "MX"), morningTime, "6:29"),
      Arguments.of(Locale("es", "MX"), afternoonTime, "14:33"),
    )
  }

  @ParameterizedTest
  @MethodSource
  fun `Return correct formatted time when use case is executed`(locale: Locale, dateObject: DateObject, expected: String) {
    // given
    useCase = FormatTimeUseCase(locale, timeZone, logger)

    // when
    val result = useCase.execute(dateObject)

    // then
    assertEquals(expected, result)
  }
}
