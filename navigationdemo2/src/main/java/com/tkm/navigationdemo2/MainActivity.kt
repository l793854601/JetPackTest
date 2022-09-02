package com.tkm.navigationdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var mFragmentContainerView: FragmentContainerView
    private lateinit var mBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFragmentContainerView = findViewById(R.id.fragment_container_view)
        mBottomNavigationView = findViewById(R.id.bottom_navigation_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        //  自带的FragmentNavigator在切换Fragment时，是以replace的方式切换的
        //  因此每次切换Fragment，会重新创建Fragment
        //  想要实现Fragment没创建则创建，否则隐藏/显示，则需要自定义FragmentNavigator
        mBottomNavigationView.setupWithNavController(navController)
    }
}