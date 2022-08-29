package com.tkm.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation

class DetailFragment : Fragment() {
    private lateinit var mTvValue: TextView
    private lateinit var mBtnShowHome: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mTvValue = view.findViewById(R.id.tv_value)
        mBtnShowHome = view.findViewById(R.id.btn_show_home)
        mBtnShowHome.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_detailFragment_to_homeFragment)
        }

        //  基于不安全的传参取值
//        mTvValue.text = arguments?.getString("KEY")

        //  基于Safe-arg的传参取值
        arguments?.let {
            val args = HomeFragmentArgs.fromBundle(it)
            val key = args.key
            val age = args.age
            mTvValue.text = "key: $key, age: $age"
        }

    }
}