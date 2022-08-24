package com.tkm.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.tkm.databinding.databinding.ActivityGirlListBinding

class GirlListActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityGirlListBinding

    private val mAdapter by lazy { GirlListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_girl_list)

        val layoutManager = LinearLayoutManager(this)
        mBinding.rv.layoutManager = layoutManager
        mBinding.rv.adapter = mAdapter

        mAdapter.setList(Girl.createDataSource())
    }
}