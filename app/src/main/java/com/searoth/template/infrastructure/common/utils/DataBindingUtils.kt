package com.searoth.template.infrastructure.common.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.squareup.picasso.Picasso
import androidx.databinding.adapters.SeekBarBindingAdapter.OnProgressChanged
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat
import com.searoth.template.R
import com.searoth.template.infrastructure.di.SeaRothServiceLocator
import java.io.File


@BindingConversion
fun convertBooleanToVisibility(visible: Boolean): Int {
    return if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["app:imageUrl", "app:placeholder"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?, placeholder: Drawable) {
    SeaRothServiceLocator.resolve(Picasso::class.java)
        .load(url)
        .placeholder(placeholder)
        .error(R.drawable.ic_404)
        .into(imageView)
}

@BindingAdapter(value = ["app:imageFilePath", "app:placeholder"], requireAll = false)
fun loadImageFilePath(imageView: ImageView, url: String?, placeholder: Drawable) {
    val lastPart = url?.substringAfterLast("/")

    if(lastPart?.toIntOrNull() != null){
        SeaRothServiceLocator.resolve(Picasso::class.java)
            .load(url)
            .placeholder(placeholder)
            .error(R.drawable.ic_404)
            .into(imageView)
    }else {
        SeaRothServiceLocator.resolve(Picasso::class.java)
            .load(File(url))
            .placeholder(placeholder)
            .error(R.drawable.ic_404)
            .into(imageView)
    }

}

@BindingAdapter(value = ["app:imageUrlTwo"], requireAll = false)
fun loadImage2(imageView: ImageView, url: String?) {
    SeaRothServiceLocator.resolve(Picasso::class.java)
        .load(url)
        .error(R.drawable.ic_404)
        .into(imageView)
}

@BindingAdapter("app:removeFocus")
fun setFocus(v: View, removeFocus: Boolean) {
    if (removeFocus) {
        v.isFocusableInTouchMode = false
        v.isFocusable = false
        v.isFocusableInTouchMode = true
        v.isFocusable = true
        closeSoftKeyboard(v)
    }
}

@BindingAdapter("android:onProgressChanged")
fun setListener(view: SeekBar, listener: OnProgressChanged) {
    setListener(view, listener)
}

fun closeSoftKeyboard(view: View) {
    val inputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.applicationWindowToken, 0)
}

fun BaseObservable.onChange(action: () -> Unit) {
    this.addOnPropertyChangedCallback(object : androidx.databinding.Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: androidx.databinding.Observable?, propertyId: Int) {
            action()
        }
    })
}

@BindingAdapter("app:layout_marginBottom")
fun setMarginBottom(view: View, margin: Int) {
    val params = view.layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.bottomMargin = margin
        view.requestLayout()
    }
}

@BindingAdapter("app:layout_marginBottom")
fun setMarginBottom(view: View, margin: Float) {
    val params = view.layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.bottomMargin = margin.toInt()
        view.requestLayout()
    }
}

@BindingAdapter("app:layout_height")
fun setLayoutHeight(view: View, height: Int) {
    val params = view.layoutParams
    params.height = height
    view.requestLayout()
}

@BindingAdapter(value = ["app:textAppearanceStyle"])
fun textAppearanceStyle(textView: TextView, @StyleRes style: Int?) {
    style?.also { TextViewCompat.setTextAppearance(textView, it) }
}

@BindingAdapter("app:layout_marginTop")
fun setMarginTop(view: View, margin: Int) {
    val params = view.layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.topMargin = margin
        view.requestLayout()
    }
}