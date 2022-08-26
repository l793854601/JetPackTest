package com.tkm.room.words

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}