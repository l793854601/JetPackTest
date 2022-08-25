package com.tkm.databinding.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
    //  使用ObservableField完成双向绑定
    val username by lazy { ObservableField<String?>() }
    val password by lazy { ObservableField<String?>() }
    //  Android Studio 3.1 及更高版本允许用 LiveData 对象替换可观察字段
    val age by lazy { MutableLiveData<String?>() }
}