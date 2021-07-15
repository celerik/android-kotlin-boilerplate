package com.app.base.usecases

import com.app.base.data.DateObject
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Locale

@ExtendWith(MockKExtension::class)
internal class GetDayUseCaseTest {

  private lateinit var useCase: GetDayUseCase

  @BeforeEach
  fun setUp() {
    useCase = GetDayUseCase(Locale.ENGLISH)
  }

  @Test
  fun `Should get correct day when date is valid`() {
    // Given
    val dateObject = DateObject(1595244561, "UTC")

    // When
    val result = useCase.execute(dateObject)

    // Then
    assertEquals("Monday", result)
  }

  @Test
  fun `Should get empty when date is not valid`() {
    // Given
    val dateObject = DateObject(0, "")

    // When
    val result = useCase.execute(dateObject)

    // Then
    assertEquals("", result)
  }
}
