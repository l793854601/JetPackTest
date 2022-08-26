package com.tkm.room.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords()

    fun insert(word: Word) {
        viewModelScope.launch {
            repository.insert(word)
        }
    }
}