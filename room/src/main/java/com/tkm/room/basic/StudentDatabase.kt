package com.tkm.room.basic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Room概念之三：数据库（Database）
 */
@Database(entities = [Student::class], version = 4, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "mydb.db"
        private var INSTANCE: StudentDatabase? = null

        //  数据库版本升级（从1到2）
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE student ADD COLUMN sex INTEGER NOT NULL DEFAULT 0")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE student ADD COLUMN bar_data INTEGER NOT NULL DEFAULT 0")
            }
        }

        fun getInstance(context: Context): StudentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, StudentDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()           //  允许主线程访问
                    .addMigrations(MIGRATION_1_2)       //  版本升级（从1到2）
                    .addMigrations(MIGRATION_2_3)
                    .fallbackToDestructiveMigration()   //  升级失败的处理（重新创建表，且数据会丢失）
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun userDao(): StudentDao
}