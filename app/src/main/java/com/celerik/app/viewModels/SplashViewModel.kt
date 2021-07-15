package com.celerik.app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.base.interfaces.Logger
import com.app.base.interfaces.SingleUseCase
import com.app.core.Event
import com.celerik.app.R
import com.celerik.app.data.SplashNews
import com.app.core.BaseViewModel
import com.app.core.exceptions.NoConnectionException
import com.app.core.interfaces.AppResources
import com.app.core.network.ServerException
import com.app.core.qualifiers.VerifyInternet
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.ConnectException
import javax.inject.Inject
import retrofit2.HttpException

class SplashViewModel @Inject constructor(
  private val logger: Logger,
  private val resources: AppResources,
  @VerifyInternet private val verifyInternetConnectivityUseCase: SingleUseCase<Unit, Boolean>,
) : BaseViewModel() {

  private val _news = MutableLiveData<Event<SplashNews>>()
  val news: LiveData<Event<SplashNews>> = _news

  fun onViewActive() {
    disposables.add(
      validateConnectivity()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _news.value = Event(SplashNews.AppInitialized)
        }) {
          handleError(it)
        }
    )
  }

  fun retryInitialize() {
    onViewActive()
  }

  private fun validateConnectivity(): Completable {
    return Completable.defer {
      verifyInternetConnectivityUseCase.execute(Unit)
        .flatMapCompletable { isConnected ->
          if (isConnected) {
            Completable.complete()
          } else {
            Completable.error(NoConnectionException)
          }
        }
    }
  }


  private fun handleError(throwable: Throwable) {
    when (throwable) {
      is ConnectException -> {
        _news.value =
          Event(SplashNews.ShowErrorNews(resources.getString(R.string.connection_error)))
      }
      is ServerException -> {
        _news.value = Event(SplashNews.ShowErrorNews(throwable.message))
      }
      is HttpException -> {
        val message = throwable.response()?.errorBody()?.string()
        _news.value = Event(SplashNews.ShowErrorNews(message.toString()))
      }
      is NoConnectionException -> {
        _news.value = Event(SplashNews.ShowNoConnectivityView)
      }
      is UpdateRequiredException -> {
        _news.value = Event(SplashNews.FinishSplashNews)
        logger.d("SplashViewModel InitializeException", throwable)
      }
      else -> {
        _news.value = Event(SplashNews.ShowErrorNews(throwable.message.toString()))
        logger.e("SplashViewModel handle error", throwable)
      }
    }
  }

  private class UpdateRequiredException : Exception()
}
