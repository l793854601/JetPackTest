package com.tkm.room.words

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class WordRepository(private val dao: WordDao) {

    fun allWords(): LiveData<List<Word>> {
        return dao.getAlphabetizedWords()
    }

    @WorkerThread
    suspend fun insert(word: Word) {
        dao.insert(word)
    }
}