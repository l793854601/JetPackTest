package com.tkm.lifecycle

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyCountdownObserver : DefaultLifecycleObserver {
    companion object {
        private const val TAG = "MyCountdownObserver"
    }

    private val mCountDownTimer = object : CountDownTimer(100 * 1000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.d(TAG, "onTick: ${millisUntilFinished / 1000}s")
        }

        override fun onFinish() {
            Log.d(TAG, "onFinish: ")
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        startCountdown()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        stopCountdown()
    }

    private fun startCountdown() {
        mCountDownTimer.start()
    }

    private fun stopCountdown() {
        mCountDownTimer.cancel()
    }
}