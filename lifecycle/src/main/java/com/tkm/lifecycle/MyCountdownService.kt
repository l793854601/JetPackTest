package com.tkm.lifecycle

import android.util.Log
import androidx.lifecycle.LifecycleService

class MyCountdownService : LifecycleService() {
    companion object {
        private const val TAG = "MyCountdownService"
    }
    init {
        Log.d(TAG, "MyCountdownService.init()")

        val observer = MyCountdownObserver()
        lifecycle.addObserver(observer)
    }
}