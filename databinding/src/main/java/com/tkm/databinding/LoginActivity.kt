package com.tkm.databinding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tkm.databinding.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val mViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel

        mBinding.btnDefault.setOnClickListener {
            mViewModel.username.set("root")
            mViewModel.password.set("root123")
            mViewModel.age.value = "18"
        }

        mBinding.btnLogin.setOnClickListener {
            val text1 = "UI 用户名：${mBinding.etUsername.text.toString().trim()}, 密码：${mBinding.etPassword.text.toString().trim()}, 年龄：${mBinding.etAge.text.toString().trim()}"
            val text2 = "ViewModel 用户名：${mViewModel.username.get()}, 密码：${mViewModel.password.get()}, 年龄：${mViewModel.age.value}"

            Toast.makeText(this, "$text1\n$text2", Toast.LENGTH_SHORT).show()
        }
    }
}