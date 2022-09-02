package com.tkm.navigationdemo1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs

class AgreementActivity : AppCompatActivity() {

    private lateinit var mTvUsername: TextView
    private lateinit var mTvPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agreement)
        window.setBackgroundDrawableResource(android.R.color.transparent)

        mTvUsername = findViewById(R.id.tv_username)
        mTvPassword = findViewById(R.id.tv_password)

        intent.extras?.let {
            val args = AgreementActivityArgs.fromBundle(it)
            mTvUsername.text = args.username
            mTvPassword.text = args.password
        }
    }
}