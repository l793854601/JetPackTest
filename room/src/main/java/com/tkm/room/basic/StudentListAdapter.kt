package com.tkm.room.basic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tkm.room.R

class StudentListAdapter : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {

    private val mList = mutableListOf<Student>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Student>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_student_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student = mList[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTvNo: TextView = itemView.findViewById(R.id.tv_no)
        private val mTvName: TextView = itemView.findViewById(R.id.tv_name)
        private val mTvAge: TextView = itemView.findViewById(R.id.tv_age)
        private val mTvSex: TextView = itemView.findViewById(R.id.tv_sex)

        fun bind(student: Student) {
            mTvNo.text = "id：${student.userId}"
            mTvName.text = "name：${student.name}"
            mTvAge.text = "age：${student.age}"
            mTvSex.text = "sex: ${student.displaySex}"
        }
    }
}