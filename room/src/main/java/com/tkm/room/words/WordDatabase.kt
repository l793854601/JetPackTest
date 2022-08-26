package com.tkm.room.words

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: WordDatabase? = null
        private const val DB_NAME = "word.db"

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    DB_NAME
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun wordDao(): WordDao

    //  数据库创建、打开灯操作时的回调
    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        //  数据库第一次创建时回调
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            wordDao.deleteAll()

            //  手动预填充数据
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World")
            wordDao.insert(word)
            word = Word("Kotlin")
            wordDao.insert(word)
        }
    }
}