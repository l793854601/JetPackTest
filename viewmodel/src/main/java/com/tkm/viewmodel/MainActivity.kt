package com.tkm.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

/*
    ViewModel的应用：
    1.屏幕旋转之后用户操作数据仍然存在（瞬态数据不会丢失）
    2.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        private const val NUM_KEY = "NUM_KEY"
    }

    private var mNum = 0

    //  完整的写法
    private val mViewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    //  引入fragment-ktx之后，可以使用属性委托创建ViewModel
//    private val mViewModel: MainViewModel by viewModels()

    private val mTvNum1: TextView by lazy { findViewById(R.id.tv_num1) }
    private val mTvNum2: TextView by lazy { findViewById(R.id.tv_num2) }
    private val mTvNum3: TextView by lazy { findViewById(R.id.tv_num3) }
    private val mBtnPlus: TextView by lazy { findViewById(R.id.btn_plus) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            mNum = it.getInt(NUM_KEY)
        }

        mTvNum1.text = "$mNum"

        mBtnPlus.setOnClickListener {
            mNum++
            mTvNum1.text = "$mNum"

            mViewModel.plus()
        }

        mViewModel.numData.observe(this) {
            mTvNum2.text = "$it"
        }

        mViewModel.countdownData.observe(this) {
            mTvNum3.text = "$it"
        }

        mViewModel.startThread()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //  不使用viewModel则需要手动实现onSaveInstanceState方法保存数据，并在onCreate方法中恢复数据
        outState.putInt(NUM_KEY, mNum)
    }
}