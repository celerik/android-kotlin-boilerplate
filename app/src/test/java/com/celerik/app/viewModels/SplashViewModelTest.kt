package com.celerik.app.viewModels

import com.app.base.interfaces.Logger
import com.app.base.interfaces.SingleUseCase
import com.app.core.Event
import com.app.core.interfaces.AppResources
import com.app.core.test.utils.InstantExecutorExtension
import com.app.core.test.utils.RxSchedulerExtension
import com.celerik.app.data.SplashNews
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(RxSchedulerExtension::class, InstantExecutorExtension::class, MockKExtension::class)
class SplashViewModelTest {

  @MockK(relaxed = true)
  private lateinit var logger: Logger

  @MockK
  private lateinit var resources: AppResources

  @MockK(relaxed = true)
  private lateinit var verifyInternetConnectivityUseCase: SingleUseCase<Unit, Boolean>

  private lateinit var viewModel: SplashViewModel

  @BeforeEach
  fun setUp() {
    viewModel = SplashViewModel(
      logger,
      resources,
      verifyInternetConnectivityUseCase,
    )
  }

  @Test
  fun `Should send network connectivity error event when getting no connection exception`() {
    // given
    every { verifyInternetConnectivityUseCase.execute(Unit) } returns Single.just(false)

    // when
    viewModel.onViewActive()

    // then
    assertEquals((viewModel.news.value as Event).peekContent(), SplashNews.ShowNoConnectivityView)
  }
}
