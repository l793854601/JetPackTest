package com.tkm.navigationdemo1.fragments

import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.tkm.navigationdemo1.R
import com.tkm.navigationdemo1.base.BaseFragment

class AvatarFragment : BaseFragment() {

    private lateinit var mBtnToLogin: Button

    override fun getLayoutResId(): Int {
        return R.layout.fragment_avatar
    }

    override fun initViews(rootView: View) {
        mBtnToLogin = rootView.findViewById(R.id.btn_to_login)
        mBtnToLogin.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_avatarFragment_to_loginFragment)
        }
    }
}