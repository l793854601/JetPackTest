package com.tkm.paging2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val viewModel: ArticleViewModel by viewModels()

    private lateinit var mRefreshLayout: SwipeRefreshLayout
    private lateinit var mRv: RecyclerView

    private val mAdapter by lazy { ArticleListAdapter() }

    private val mHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRefreshLayout = findViewById(R.id.refresh_layout)

        mRv = findViewById(R.id.rv)
        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter = mAdapter

        viewModel.pagedListArticle.observe(this) {
            mAdapter.submitList(it)
            stopRefresh()
        }

        mRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            startRefresh()
        }
    }

    private fun startRefresh() {
        mHandler.postDelayed({
            mRefreshLayout.isRefreshing = true
        }, 500)
    }

    private fun stopRefresh() {
        mHandler.postDelayed({
            mRefreshLayout.isRefreshing = false
        }, 500)
    }
}