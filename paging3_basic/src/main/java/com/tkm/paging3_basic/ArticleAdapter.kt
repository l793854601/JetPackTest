package com.tkm.paging3_basic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tkm.paging3_basic.databinding.LayoutArticleListBinding

class ArticleAdapter : PagingDataAdapter<Article, ArticleAdapter.ViewHolder>(ARTICLE_DIFF_CALLBACK) {
    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        article?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutArticleListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: LayoutArticleListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvDate.text = article.created?.toString()
        }
    }
}