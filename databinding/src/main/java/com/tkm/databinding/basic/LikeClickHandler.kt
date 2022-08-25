package com.tkm.databinding.basic

import android.content.Context
import android.view.View
import android.widget.Toast

class LikeClickHandler(private val context: Context) {

    fun like(view: View) {
        Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show()
    }
}