package com.celerik.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.celerik.app.databinding.ActivityMainBinding
import com.celerik.components.utils.viewBinding

class MainActivity : AppCompatActivity() {

  private val binding by viewBinding(ActivityMainBinding::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
  }
}
