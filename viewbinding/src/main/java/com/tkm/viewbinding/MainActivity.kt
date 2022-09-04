package com.tkm.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkm.viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.tvHello.text = "Hello, ViewBinding!"

        mBinding.btn.setOnClickListener {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
        }

        val layoutManager = LinearLayoutManager(this)
        mBinding.rv.layoutManager = layoutManager
        mBinding.rv.adapter = TextListAdapter()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, SubFragment())
            .commitNow()
    }
}