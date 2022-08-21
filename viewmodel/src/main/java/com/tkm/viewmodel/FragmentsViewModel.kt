package com.tkm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentsViewModel : ViewModel() {
    val valueData by lazy { MutableLiveData<Int>() }
}