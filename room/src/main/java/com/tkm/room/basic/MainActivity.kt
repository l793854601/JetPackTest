package com.tkm.room.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tkm.room.R

/**
 * Room重要概念：
 * 1.Entity：实体类，对应的是数据库的一张表结构，使用注解@Entity标记
 * 2.DAO：包含访问一系列访问数据库的方法（增删改查），使用注解@Dao标记
 * 3.Database：数据库持有者，作为与应用持久化相关数据的底层连接的主要接入点，使用注解@Database标记
 * Database需要班组以下条件：
 * 1.定义的类必须一个继承自RoomDatabase的抽象类
 * 2.在注解@Database中定义对应的实体类
 * 3.包含一个没有参数的抽象方法，返回Dao
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mRv: RecyclerView

    private val mAdapter by lazy { StudentListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRv = findViewById(R.id.rv)
        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter = mAdapter
    }

    fun onInsertClick(view: View) {
        listOf(
            Student(userId = 2, name = "Jenny", age = 18, 0, 0),
            Student(name = "Kate", age = 17),
            Student(name = "Jessica", age = 19)
        ).toTypedArray().let {
            StudentDatabase.getInstance(this)
                .userDao()
                .insertAll(*it)
        }

        //  查询
        onQueryClick(view)
    }

    fun onDeleteClick(view: View) {
        StudentDatabase.getInstance(this)
            .userDao()
            .delete(Student(userId = 1))

        //  查询
        onQueryClick(view)
    }

    fun onUpdateClick(view: View) {
        StudentDatabase.getInstance(this)
            .userDao()
            .updateUsers(Student(userId = 2, name = "TKM", age = 24, 0, 0))

        //  查询
        onQueryClick(view)
    }

    fun onQueryClick(view: View) {
        val students = StudentDatabase.getInstance(this)
            .userDao()
            .getAll()
        mAdapter.setList(students)
    }
}