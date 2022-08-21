package com.tkm.lifecycle

import android.content.Context
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.Chronometer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MyChronometer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Chronometer(context, attrs, defStyleAttr), LifecycleEventObserver {

    private var mElapsedTime: Long = 0

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            onResume()
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            onPause()
        }
    }

    private fun onResume() {
        base = SystemClock.elapsedRealtime() - mElapsedTime
        start()
    }

    private fun onPause() {
        mElapsedTime = SystemClock.elapsedRealtime() - base
        stop()
    }
}