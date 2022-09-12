package com.tkm.paging3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class FooterAdapter : LoadStateAdapter<FooterAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_footer, parent, false)
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mProgressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
        private val mTv: TextView = itemView.findViewById(R.id.tv)

        fun bind(loadState: LoadState) {
            when (loadState) {
                is LoadState.Loading -> {
                    //  正在加载
                    mProgressBar.visibility = View.VISIBLE
                    mTv.visibility = View.VISIBLE
                    mTv.text = "正在加载..."
                }
                is LoadState.NotLoading -> {
                    //  未加载
                    mProgressBar.visibility = View.VISIBLE
                    mTv.visibility = View.VISIBLE
                    mTv.text = "上拉加载更多"
                }
                is LoadState.Error -> {
                    //  加载失败
                    mProgressBar.visibility = View.VISIBLE
                    mTv.visibility = View.VISIBLE
                    mTv.text = "加载失败，请稍后重试"
                }
            }
        }
    }

}