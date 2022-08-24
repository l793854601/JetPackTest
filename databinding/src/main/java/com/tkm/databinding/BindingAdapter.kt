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

/**
 * value：指明xml中的属性
 * requireAll：属性是否全部都在xml中赋值
 */
@BindingAdapter(value = ["remoteAvatar", "error"], requireAll = false)
fun setRemoteAvatar(view: ImageView, url: String?, @DrawableRes error: Int?) {
    Glide.with(view)
        .load(url)
        .placeholder(error ?: R.mipmap.ic_launcher)
        .error(error ?: R.mipmap.ic_launcher)
        .into(view)
}