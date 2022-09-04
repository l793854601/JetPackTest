package com.tkm.navigationdemo1.fragments

import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.tkm.navigationdemo1.R
import com.tkm.navigationdemo1.base.BaseFragment

class RegisterFragment : BaseFragment() {
    private lateinit var mBtnToAvatar: Button
    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText

    private var mUsername: String? = null
    private var mPassword: String? = null

    override fun getLayoutResId(): Int {
        return R.layout.fragment_register
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)

        arguments?.let {
            mUsername = it.getString("USER_NAME")
            mPassword = it.getString("PASSWORD")
        }
    }

    override fun initViews(rootView: View) {
        mBtnToAvatar = rootView.findViewById(R.id.btn_to_avatar)
        mEtUsername = rootView.findViewById(R.id.et_username)
        mEtPassword = rootView.findViewById(R.id.et_password)

        mEtUsername.setText(mUsername)
        mEtPassword.setText(mPassword)

        mBtnToAvatar.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_registerFragment_to_avatarFragment)
        }
    }
}