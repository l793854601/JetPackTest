package com.tkm.room.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//  因为WordViewModel的创建需要WordRepository，所以需要自定义WordViewModelFactory
class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}