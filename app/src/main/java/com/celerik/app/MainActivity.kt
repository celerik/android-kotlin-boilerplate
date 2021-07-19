package com.celerik.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.base.interfaces.Logger
import com.celerik.app.databinding.ActivityMainBinding
import com.celerik.components.utils.viewBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var logger: Logger

  private val binding by viewBinding(ActivityMainBinding::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    logger.d("MainActivity started")
  }
}
