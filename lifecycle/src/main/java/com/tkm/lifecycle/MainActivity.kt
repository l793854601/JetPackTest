package com.tkm.lifecycle

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

/*
    LifeCycle应用：
    1.使用LifeCycle解耦页面与组件
    2.使用LifeService解耦Service与组件
    3.使用ProcessLifeCycleOwner监听应用程序生命周期
 */
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val mChronometer1: Chronometer by lazy { findViewById(R.id.chronometer1) }
    private val mChronometer2: MyChronometer by lazy { findViewById(R.id.chronometer2) }

    private var mElapsedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mChronometer1.setOnChronometerTickListener {
            val seconds = (SystemClock.elapsedRealtime() - it.base) / 1000
            Log.d(TAG, "mChronometer1 onTick: $seconds")
        }

        mChronometer2.setOnChronometerTickListener {
            val seconds = (SystemClock.elapsedRealtime() - it.base) / 1000
            Log.d(TAG, "mChronometer2 onTick: $seconds")
        }

        //  添加LifeCycle监听
        lifecycle.addObserver(mChronometer2)
    }

    override fun onResume() {
        super.onResume()
        mChronometer1.base = SystemClock.elapsedRealtime() - mElapsedTime
        mChronometer1.start()
    }

    override fun onPause() {
        super.onPause()
        mElapsedTime = SystemClock.elapsedRealtime() - mChronometer1.base
        mChronometer1.stop()
    }

    fun start(view: View) {
        val intent = Intent(this, MyCountdownService::class.java)
        startService(intent)
    }

    fun stop(view: View) {
        val intent = Intent(this, MyCountdownService::class.java)
        stopService(intent)
    }
}