package com.tkm.databinding.basic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tkm.databinding.girls.GirlListActivity
import com.tkm.databinding.login.LoginActivity
import com.tkm.databinding.R
import com.tkm.databinding.scoreboard.ScoreBoardActivity
import com.tkm.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this

        val idol = Idol(
            R.drawable.cyndi,
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwx3.sinaimg.cn%2Fmw690%2F006oXOs3gy1h4w5361d2aj32m83xc7wm.jpg&refer=http%3A%2F%2Fwx3.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663680945&t=7680a10933402f36f1d9221db3cfe8a7",
            "王心凌",
            10)
        mBinding.idol = idol
        mBinding.likeClickHandler = LikeClickHandler(this)

        mBinding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnGirls.setOnClickListener {
            val intent = Intent(this, GirlListActivity::class.java)
            startActivity(intent)
        }

        mBinding.btnScores.setOnClickListener {
            val intent = Intent(this, ScoreBoardActivity::class.java)
            startActivity(intent)
        }
    }
}