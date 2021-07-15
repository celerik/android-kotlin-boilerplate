package com.app.base.usecases

import com.app.base.data.DateObject
import com.app.base.interfaces.Logger
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Locale
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class FormatDateUseCaseTest {

  @MockK(relaxed = true)
  private lateinit var logger: Logger

  private lateinit var useCase: FormatDateUseCase

  @BeforeEach
  fun setUp() {
    useCase = FormatDateUseCase(Locale.CANADA, logger)
  }

  @Test
  fun `Map delivery date with the correct date format`() {
    // given
    val dateObject = DateObject(1595244561, "UTC")

    // when
    val result = useCase.execute(dateObject)

    // then
    assertEquals("20/07/20", result)
  }

  @Test
  fun `Return an empty String when the delivery date is empty or can not be parsed`() {
    // given
    val dateObject = DateObject(0, "UTC")

    // when
    val result = useCase.execute(dateObject)

    // then
    assertEquals("", result)
  }

  @Test
  fun `Return an empty String when the use case has an exception`() {
    // given
    useCase = FormatDateUseCase(mockk(), logger)

    val dateObject = DateObject(1595244561, "UTC")

    // when
    val result = useCase.execute(dateObject)

    // then
    assertEquals("", result)
  }
}
