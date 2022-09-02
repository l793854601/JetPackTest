package com.tkm.navigationdemo1.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.tkm.navigationdemo1.AgreementActivityArgs
import com.tkm.navigationdemo1.R
import com.tkm.navigationdemo1.base.BaseFragment

class LoginFragment : BaseFragment() {
    private lateinit var mIvAvatar: ImageView
    private lateinit var mEtUsername: EditText
    private lateinit var mEtPassword: EditText
    private lateinit var mBtnToRegister: Button
    private lateinit var mBtnToForget: Button
    private lateinit var mBtnToAgreement: Button

    override fun getLayoutResId(): Int {
        return R.layout.fragment_login
    }

    override fun initViews(rootView: View) {
        mIvAvatar = rootView.findViewById(R.id.iv_avatar)
        mEtUsername = rootView.findViewById(R.id.et_username)
        mEtPassword = rootView.findViewById(R.id.et_password)
        mBtnToRegister = rootView.findViewById(R.id.btn_to_register)
        mBtnToForget = rootView.findViewById(R.id.btn_to_forget)
        mBtnToAgreement = rootView.findViewById(R.id.btn_to_agreement)

        mBtnToRegister.setOnClickListener {
//            val navController = NavHostFragment.findNavController(this)
//            val navController = findNavController()
//            val navController = Navigation.findNavController(it)

            //  使用Bundle进行不安全的传参
            val args = Bundle().also { bundle ->
                bundle.putString("USER_NAME", mEtUsername.text.toString().trim())
                bundle.putString("PASSWORD", mEtPassword.text.toString().trim())
            }

            //  与Fragment共享元素
            val extra = FragmentNavigatorExtras(
                mIvAvatar to "userAvatarTn",
                mEtUsername to "usernameTn",
                mEtPassword to "passwordTn"
            )

            val navController = Navigation.findNavController(requireActivity(), it.id)

            navController.navigate(
                R.id.action_loginFragment_to_registerFragment,
                args,
                null,
                extra
            )
        }

        mBtnToForget.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_forgetFragment)
        }

        mBtnToAgreement.setOnClickListener {
            //  与Activity共享元素
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(requireActivity(), mIvAvatar, "userAvatarTn")
            val extra = ActivityNavigator.Extras.Builder()
                .setActivityOptions(options)
                .build()

            //  使用safe-args传参
            val args = LoginFragmentDirections.actionLoginFragmentToAgreementActivity(
                username = mEtUsername.text.toString().trim(),
                password = mEtPassword.text.toString().trim()
            ).arguments

            val navController = findNavController()
            //  args被放在了Intent的extra中
            navController.navigate(R.id.action_loginFragment_to_agreementActivity, args, null, extra)
        }
    }
}