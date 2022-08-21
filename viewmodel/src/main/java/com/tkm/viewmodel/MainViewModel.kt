package com.tkm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

//  如果要在ViewModel中使用Application上下文，为了避免内存泄漏，需要使用AndroidViewModel
class MainViewModel : ViewModel() {

    val numData by lazy { MutableLiveData(0) }

    val countdownData by lazy { MutableLiveData(0) }

    private val timer = Timer()

    fun startThread() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                val value = countdownData.value ?: 0
                countdownData.postValue(value + 1)
            }
        }, 0, 1000)
    }

    fun plus() {
        val value = numData.value ?: 0
        numData.value = (value + 1)
    }

    override fun onCleared() {
        timer.cancel()
    }
}