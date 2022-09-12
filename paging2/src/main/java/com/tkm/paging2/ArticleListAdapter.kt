package com.tkm.paging2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ArticleListAdapter : PagedListAdapter<Article, ArticleListAdapter.ViewHolder>(ITEM_CALL_BACK) {

    companion object {
        private val ITEM_CALL_BACK = object : DiffUtil.ItemCallback<Article>() {

            //  是否为同一个对象（可以判断id是否一致，也可以判断引用是否相同）
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            //  内容是否相同
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_article_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mTvTitle: TextView = itemView.findViewById(R.id.tv_title)

        fun bind(article: Article) {
            mTvTitle.text = article.title
        }
    }
}