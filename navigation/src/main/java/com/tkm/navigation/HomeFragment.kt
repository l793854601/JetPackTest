package com.tkm.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class HomeFragment : Fragment() {
    companion object {
        private const val TAG = "HomeFragment"
    }

    private lateinit var mBtnShowDetail: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        mBtnShowDetail = view.findViewById(R.id.btn_show_detail)
        mBtnShowDetail.setOnClickListener {
//            //  不安全的传参
//            val args = Bundle()
//            args.putString("KEY", "Hello World")
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.action_homeFragment_to_detailFragment, args)

            //  基于Safe arg的传参
            val bundle = HomeFragmentArgs(key = "Hello, World", age = 20).toBundle()
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }
}