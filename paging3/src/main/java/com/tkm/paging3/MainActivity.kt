package com.tkm.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: ArticleViewModel by viewModels()

    private val mRv: RecyclerView by lazy { findViewById(R.id.rv) }
    private val mAdapter by lazy { ArticleListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRv.adapter = mAdapter.withLoadStateFooter(FooterAdapter())

        lifecycleScope.launch {
            viewModel.loadArticleList().collectLatest {
                mAdapter.submitData(it)
            }
        }
    }
}