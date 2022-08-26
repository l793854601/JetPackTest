package com.tkm.room.words

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(
    @PrimaryKey
    @ColumnInfo(name = "word", typeAffinity = ColumnInfo.TEXT)
    val word: String
)
