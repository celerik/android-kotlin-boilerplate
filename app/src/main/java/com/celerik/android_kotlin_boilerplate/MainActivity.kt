package com.celerik.android_kotlin_boilerplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.celerik.android_kotlin_boilerplate.databinding.ActivityMainBinding
import com.celerik.components.utils.viewBinding

class MainActivity : AppCompatActivity() {

  private val binding by viewBinding(ActivityMainBinding::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
  }
}
