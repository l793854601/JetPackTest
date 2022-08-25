package com.tkm.room.basic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room概念之三：数据库（Database）
 */
@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "mydb.db"
        private var instance: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase {
            val tmpInstance = instance
            if (tmpInstance != null) {
                return tmpInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, StudentDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()   //  允许主线程访问
                    .build()
                Companion.instance = instance
                return instance
            }
        }
    }

    abstract fun userDao(): StudentDao
}