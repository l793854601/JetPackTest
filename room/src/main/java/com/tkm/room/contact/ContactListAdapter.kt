package com.tkm.room.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tkm.room.R

class ContactListAdapter : ListAdapter<Contact, ContactListAdapter.ViewHolder>(Callback()) {

    fun interface OnItemClickedListener {
        fun onItemClicked(adapter: ContactListAdapter, contact: Contact)
    }

    private var mOnItemClickedListener: OnItemClickedListener? = null

    fun setOnItemClickedListener(listener: OnItemClickedListener) {
        mOnItemClickedListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_contact_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val c = getItem(position)
            mOnItemClickedListener?.onItemClicked(this, c)
        }

        val contact = getItem(position)
        holder.bind(contact)
    }

    class Callback: DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTvNo: TextView
        private val mTvName: TextView
        private val mTvPhone: TextView

        init {
            mTvNo = itemView.findViewById(R.id.tv_no)
            mTvName = itemView.findViewById(R.id.tv_name)
            mTvPhone = itemView.findViewById(R.id.tv_phone)
        }

        fun bind(contact: Contact) {
            mTvNo.text = "编号：${contact.id}"
            mTvName.text = "姓名：${contact.displayName}"
            mTvPhone.text = "电话号码：${contact.phone}"
        }

    }
}