package com.tkm.databinding

import androidx.annotation.DrawableRes

data class Idol(
    @DrawableRes
    val icon: Int,

    val avatar: String?,

    val name: String?,

    val star: Int
)