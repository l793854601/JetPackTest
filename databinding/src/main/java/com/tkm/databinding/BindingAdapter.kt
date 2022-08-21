package com.tkm.databinding

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/*
    自定义BindingAdapter：
    需要在app层级的build.gradle中引入kotlin-kapt
 */

@BindingAdapter("avatar")
fun setAvatar(view: AppCompatImageView, @DrawableRes avatar: Int) {
    view.setImageResource(avatar)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("star")
fun setStar(view: TextView, star: Int) {
    view.text = "${star}星"
}

@BindingAdapter(value = ["remoteAvatar", "error"], requireAll = true)
fun setRemoteAvatar(view: ImageView, url: String?, @DrawableRes error: Int) {
    Glide.with(view)
        .load(url)
        .placeholder(error)
        .error(error)
        .into(view)
}