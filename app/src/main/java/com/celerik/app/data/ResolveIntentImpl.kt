package com.celerik.app.data

import android.content.Intent
import android.net.Uri
import com.app.core.interfaces.IntentResolver
import com.celerik.app.BuildConfig

class ResolveIntentImpl : IntentResolver {
  override fun resolveIntent(host: String): Intent {
    val url = "${BuildConfig.SCHEME}://$host"
    return Intent(Intent.ACTION_VIEW, Uri.parse(url))
  }
}
