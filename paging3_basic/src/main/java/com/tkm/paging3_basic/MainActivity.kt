package com.tkm.paging3_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.tkm.paging3_basic.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        val viewModel by viewModels<ArticleViewModel>(
            factoryProducer = { ArticleViewModelFactory(ArticleRepository()) }
        )

        val items = viewModel.items
        val articleAdapter = ArticleAdapter()
        binding.bindAdapter(articleAdapter)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                articleAdapter.loadStateFlow.collectLatest {
                    Log.d(TAG, "loadState: $it")
                    binding.refreshProgress.isVisible = it.source.refresh is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                items.collectLatest {
                    articleAdapter.submitData(it)
                }
            }
        }
    }

    private fun ActivityMainBinding.bindAdapter(adapter: ArticleAdapter) {
        rv.adapter = adapter

        val decoration = DividerItemDecoration(rv.context, DividerItemDecoration.VERTICAL)
        rv.addItemDecoration(decoration)
    }
}





















