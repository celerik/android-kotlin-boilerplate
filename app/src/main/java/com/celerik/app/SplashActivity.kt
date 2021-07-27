package com.celerik.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.base.interfaces.Logger
import com.app.base.others.ONE_SECOND_IN_MILLISECONDS
import com.app.core.EventObserver
import com.celerik.app.data.SplashNews
import com.celerik.app.databinding.ActivitySplashBinding
import com.celerik.app.viewModels.SplashViewModel
import com.celerik.components.utils.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Represents splash activity.
 *
 * This is the first screen the user will watch.
 */
class SplashActivity : AppCompatActivity() {

  @Inject
  lateinit var logger: Logger

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  private val viewModel: SplashViewModel by viewModels { viewModelFactory }

  private val binding by viewBinding(ActivitySplashBinding::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)

    super.onCreate(savedInstanceState)

    setContentView(binding.root)

    showVersionName()

    initializeSubscription()
    initializeApp()

    logger.d("SplashActivity started")
  }

  private fun initializeApp() {
    Handler(Looper.getMainLooper()).postDelayed(
      {
        viewModel.onViewActive()
      },
      ONE_SECOND_IN_MILLISECONDS
    )
  }

  private fun initializeSubscription() {
    viewModel.news.observe(this, EventObserver { handleNews(it) })
  }

  private fun showVersionName() {
    binding.textViewVersion.text = "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
  }

  private fun handleAppInitialized() {
    val mainIntent = Intent(this, MainActivity::class.java)
    startActivity(mainIntent)
    finish()
  }

  private fun handleNews(news: SplashNews) {
    when (news) {
      is SplashNews.AppInitialized -> {
        handleAppInitialized()
      }
      is SplashNews.ShowErrorNews -> {
        Snackbar.make(binding.root, news.errorMessage, Snackbar.LENGTH_INDEFINITE).show()
      }
      is SplashNews.ShowNoConnectivityView -> {
        showNoConnectionAlert()
      }
      is SplashNews.FinishSplashNews -> {
        finish()
      }
    }
  }

  private fun showNoConnectionAlert() {
    val title = resources.getString(R.string.no_internet_title)
    val message = resources.getString(R.string.no_internet_description)
    val positiveButtonText = resources.getString(R.string.no_internet_retry_button)

    val dialog: AlertDialog.Builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
    dialog.apply {
      setTitle(title)
      setMessage(message)
      setCancelable(false)
      setPositiveButton(positiveButtonText) { dialog, _ ->
        viewModel.retryInitialize()
        dialog.dismiss()
      }
      show()
    }
  }
}
