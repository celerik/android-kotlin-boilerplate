package com.app.base.usecases

import com.app.base.None
import com.app.base.Some
import com.app.base.data.CountryInfo
import com.app.base.data.Currency
import com.app.base.others.DEFAULT_CURRENCY_SYMBOL
import com.app.base.others.DEFAULT_CURRENCY_THOUSANDS_SEPARATOR
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class FormatCurrencyUseCaseTest {

  @MockK
  private lateinit var countryRepository: CountryRepository

  private lateinit var useCase: FormatCurrencyUseCase

  @BeforeEach
  fun setUp() {
    useCase = FormatCurrencyUseCase(countryRepository)
  }

  @Test
  fun `Should add correct decimals when decimalPrecision is not zero`() {
    // given
    val country = Some(
      CountryInfo(
        countryCode = "",
        mobileCountryCode = "",
        flagUrl = "",
        apiGateway = "",
        countryName = "",
        termsAndConditions = "",
        webShop = "",
        currency = Currency(
          thousandsSeparator = ".",
          decimalSeparator = ",",
          decimalPrecision = 2,
          symbol = "$ ",
          symbolAsPrefix = true,
          isoCode = ""
        ),
        mobileNumberValidator = null,
        supportPhone = null
      )
    )
    every { countryRepository.getSelectedCountry() } returns Observable.just(country)

    // when
    val formattedCurrency = useCase.execute(40000.0)

    // then
    assertEquals(formattedCurrency, "$ 40.000,00")
  }

  @Test
  fun `Should return exception when no country available`() {
    // given
    val country = None

    every { countryRepository.getSelectedCountry() } returns Observable.just(country)

    // when
    var hasException = false

    try {
      useCase.execute(40000.0)
    } catch (e: Exception) {
      hasException = true
    }

    // then
    assert(hasException)
  }

  @Test
  fun `Should remove decimals when decimalPrecision is zero`() {
    // given
    val country = Some(
      CountryInfo(
        countryCode = "",
        mobileCountryCode = "",
        flagUrl = "",
        apiGateway = "",
        countryName = "",
        termsAndConditions = "",
        webShop = "",
        currency = Currency(
          thousandsSeparator = ".",
          decimalSeparator = ",",
          decimalPrecision = 0,
          symbol = "$ ",
          symbolAsPrefix = true,
          isoCode = ""
        ),
        mobileNumberValidator = null,
        supportPhone = null
      )
    )
    every { countryRepository.getSelectedCountry() } returns Observable.just(country)

    // when
    val formattedCurrency = useCase.execute(40000.0)

    // then
    assertEquals(formattedCurrency, "$ 40.000")
  }

  @Test
  fun `Should format correctly when the number is zero`() {
    // given
    val country = Some(
      CountryInfo(
        countryCode = "",
        mobileCountryCode = "",
        flagUrl = "",
        apiGateway = "",
        countryName = "",
        termsAndConditions = "",
        webShop = "",
        currency = Currency(
          thousandsSeparator = ".",
          decimalSeparator = ",",
          decimalPrecision = 2,
          symbol = "$ ",
          symbolAsPrefix = true,
          isoCode = ""
        ),
        mobileNumberValidator = null,
        supportPhone = null
      )
    )
    every { countryRepository.getSelectedCountry() } returns Observable.just(country)

    // when
    val formattedCurrency = useCase.execute(0.0)

    // then
    assertEquals(formattedCurrency, "$ 0,00")
  }

  @Test
  fun `Should round up when rounding with decimal precision`() {
    // given
    val country = Some(
      CountryInfo(
        countryCode = "",
        mobileCountryCode = "",
        flagUrl = "",
        apiGateway = "",
        countryName = "",
        termsAndConditions = "",
        webShop = "",
        currency = Currency(
          thousandsSeparator = ".",
          decimalSeparator = ",",
          decimalPrecision = 2,
          symbol = "$ ",
          symbolAsPrefix = true,
          isoCode = ""
        ),
        mobileNumberValidator = null,
        supportPhone = null
      )
    )
    every { countryRepository.getSelectedCountry() } returns Observable.just(country)

    // when
    val formattedCurrency = useCase.execute(0.009)

    // then
    assertEquals(formattedCurrency, "$ 0,01")
  }

  @Test
  fun `Should use default currency when selected country has no currency`() {
    // given
    val country = Some(
      CountryInfo(
        countryCode = "",
        mobileCountryCode = "",
        flagUrl = "",
        apiGateway = "",
        countryName = "",
        termsAndConditions = "",
        webShop = "",
        currency = null,
        mobileNumberValidator = null,
        supportPhone = null
      )
    )
    every { countryRepository.getSelectedCountry() } returns Observable.just(country)

    // when
    val formattedCurrency = useCase.execute(4999.0)

    // then
    assertEquals(
      formattedCurrency,
      "${DEFAULT_CURRENCY_SYMBOL}4${DEFAULT_CURRENCY_THOUSANDS_SEPARATOR}999"
    )
  }

  @Test
  fun `Should throw an exception when no country is available`() {
    // given
    every { countryRepository.getSelectedCountry() } returns Observable.just(None)

    // then
    assertThrows<java.lang.Exception> {
      useCase.execute(4599.0)
    }
  }
}
