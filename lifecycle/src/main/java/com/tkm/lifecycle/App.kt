package com.tkm.lifecycle

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        //  本质为对整个App中Activity的监听
        //  ON_DESTROY永远不会被调用
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }
}