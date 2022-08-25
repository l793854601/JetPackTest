package com.tkm.databinding.scoreboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tkm.databinding.R
import com.tkm.databinding.databinding.ActivityScoreBoardBinding

class ScoreBoardActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityScoreBoardBinding

    private val mViewModel by lazy { ViewModelProvider(this)[ScoreBoardViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_score_board)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel
    }
}