package com.celerik.app.data

sealed class SplashNews {
  object AppInitialized : SplashNews()
  object ShowNoConnectivityView : SplashNews()
  object FinishSplashNews : SplashNews()
  data class ShowErrorNews(val errorMessage: String) : SplashNews()
}
