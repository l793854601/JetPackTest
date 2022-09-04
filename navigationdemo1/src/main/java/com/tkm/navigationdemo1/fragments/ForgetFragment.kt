package com.tkm.navigationdemo1.fragments

import android.view.View
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.tkm.navigationdemo1.R
import com.tkm.navigationdemo1.base.BaseFragment

class ForgetFragment : BaseFragment() {

    private lateinit var mBtnBack: Button

    override fun getLayoutResId(): Int {
        return R.layout.fragment_forget
    }

    override fun initViews(rootView: View) {
        mBtnBack = rootView.findViewById(R.id.btn_back)
        mBtnBack.setOnClickListener {
//            findNavController().popBackStack()
            findNavController().navigateUp()
        }
    }
}