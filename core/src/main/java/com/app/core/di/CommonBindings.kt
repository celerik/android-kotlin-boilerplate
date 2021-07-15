package com.app.core.di

import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("loadImagePath", "errorImage", requireAll = false)
fun bindingUriIntoImageView(imageView: ImageView, imagePath: String?, errorImage: Drawable?) {
  Glide.with(imageView.context)
    .load(imagePath)
    .error(errorImage)
    .into(imageView)
}

interface BindableAdapter<T> {
  fun setData(data: T)
}

@BindingAdapter("data")
@Suppress("UNCHECKED_CAST")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
  (recyclerView.adapter as? BindableAdapter<T>)?.setData(data)
}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
  view.isVisible = visible
}

@BindingAdapter("linkText", "onLinkClick", "text", requireAll = false)
fun setLinkToTextView(
  view: TextView,
  linkText: String,
  onLinkClick: View.OnClickListener,
  text: CharSequence
) {
  val spannableString = text.toSpannable()

  spannableString.findAnyOf(listOf(linkText))?.let {
    val linkStart = it.first
    val linkEnd = it.first + it.second.length
    spannableString[linkStart..linkEnd] = object : ClickableSpan() {
      override fun onClick(widget: View) {
        onLinkClick.onClick(view)
      }
    }
  }
  view.movementMethod = LinkMovementMethod.getInstance()
  view.text = spannableString
}
