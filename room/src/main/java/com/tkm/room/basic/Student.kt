package com.tkm.room.basic

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Room概念之一：数据实体类（Entity）
 */
@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id", typeAffinity = ColumnInfo.INTEGER)
    var userId: Int,

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    val name: String?,

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    val age: Int?
) {
    /**
     * 仅测试用
     */
    @Ignore
    constructor(name: String?, age: Int) : this(0, name, age)

    @Ignore
    constructor(userId: Int) : this(userId, null, 0)
}
