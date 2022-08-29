package com.tkm.room.contact

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "contact.db"

        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context, coroutineScope: CoroutineScope): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    DB_NAME
                ).addCallback(Callback(coroutineScope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class Callback(private val coroutineScope: CoroutineScope): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                populateDatabase(database.getContactDao())
            }
        }

        private fun populateDatabase(dao: ContactDao) {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    dao.insert(Contact("陈", "语", "18877776666"))
                    dao.insert(Contact("董", "昊", "18655443322"))
                    dao.insert(Contact("大", "未", "18900000000"))
                    dao.insert(Contact("小", "未", "18900000001"))
                    dao.insert(Contact("无", "夕", "18900000002"))
                    dao.insert(Contact("有", "手", "18900000003"))
                    dao.insert(Contact("筱", "昂", "18900000004"))
                    dao.insert(Contact("幼", "再", "18900000005"))
                    dao.insert(Contact("松", "容", "18900000006"))
                    dao.insert(Contact("笋", "阶", "18900000007"))
                }
            }
        }
    }

    abstract fun getContactDao(): ContactDao

}