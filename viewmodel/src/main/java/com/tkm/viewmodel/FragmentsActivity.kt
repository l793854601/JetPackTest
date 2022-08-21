package com.tkm.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragments)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container1, Fragment1())
            .add(R.id.fragment_container2, Fragment2())
            .commitNow()
    }
}