package com.tkm.room.basic

import androidx.room.*

/**
 * Room概念之二：数据访问对象（DAO）
 */
@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
    fun getAll(): List<Student>

    @Query("SELECT * FROM students WHERE user_id in (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Student>

    @Query("SELECT * FROM students WHERE name LIKE :name")
    fun findByName(name: String): List<Student>?

    @Query("SELECT * FROM students WHERE age is :age")
    fun findByAge(age: Int): List<Student>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg students: Student)

    /**
     * 会根据传入的student对象的主键（userId）更新
     * 如果没有相同主键的行，则不会更新
     */
    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateUsers(vararg students: Student)

    /**
     * 会根据传入的student对象的主键（userId）删除
     * 如果没有相同主键的行，则不会删除
     */
    @Delete
    fun delete(student: Student)


}