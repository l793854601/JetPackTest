package com.tkm.navigationdemo1.fragments

import android.view.View
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.tkm.navigationdemo1.R
import com.tkm.navigationdemo1.base.BaseFragment

class LoginFragment : BaseFragment() {
    private lateinit var mBtnToRegister: Button
    private lateinit var mBtnToForget: Button
    private lateinit var mBtnToAgreement: Button

    override fun getLayoutResId(): Int {
        return R.layout.fragment_login
    }

    override fun initViews(rootView: View) {
        mBtnToRegister = rootView.findViewById(R.id.btn_to_register)
        mBtnToForget = rootView.findViewById(R.id.btn_to_forget)
        mBtnToAgreement = rootView.findViewById(R.id.btn_to_agreement)

        mBtnToRegister.setOnClickListener {
//            val navController = NavHostFragment.findNavController(this)
//            val navController = findNavController()
//            val navController = Navigation.findNavController(it)
            val navController = Navigation.findNavController(requireActivity(), it.id)
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }

        mBtnToForget.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_forgetFragment)
        }

        mBtnToAgreement.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_agreementActivity)
        }
    }
}