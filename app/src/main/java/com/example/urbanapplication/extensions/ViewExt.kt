package com.example.urbanapplication.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.urbanapplication.R
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.isTablet(): Boolean = resources.getBoolean(R.bool.isTablet)

fun AppCompatActivity.isTablet(): Boolean = resources.getBoolean(R.bool.isTablet)

fun AppCompatActivity.extendViewUnderStatusBar() {
    window.apply {
        attributes = window.attributes.apply { flags = flags and FLAG_TRANSLUCENT_STATUS.inv() }
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}

fun <T : ViewDataBinding> ViewGroup.bind(layoutId: Int, attachToParent: Boolean = false): T =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, this, attachToParent)

@BindingAdapter("htmlText")
fun TextView.htmlText(text: String?) {
    setText("")
    text?.let { setText(HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_COMPACT)) }
}

@BindingAdapter("dateText")
fun TextView.htmlText(date: Calendar?) {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    date?.time?.let { text = sdf.format(it) }
}

@BindingAdapter("showView")
fun View.toggleVisibility(show: Boolean?) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
        else -> View.INVISIBLE
    }
}