package com.tkm.room.words

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tkm.room.App
import com.tkm.room.R

class WordListActivity : AppCompatActivity() {
    companion object {
        private const val RC_NEW_WORD = 100
    }

    private val mViewModel by lazy {
        ViewModelProvider(this, WordViewModelFactory((application as App).wordRepository))[WordViewModel::class.java]
    }

    private lateinit var mRv: RecyclerView
    private lateinit var mBtnAdd: FloatingActionButton
    private val mAdapter by lazy { WordListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)

        mRv = findViewById(R.id.rv)
        mBtnAdd = findViewById(R.id.btn_add)

        val layoutManager = LinearLayoutManager(this)
        mRv.layoutManager = layoutManager
        mRv.adapter = mAdapter

        mViewModel.allWords.observe(this) {
            mAdapter.submitList(it)
        }

        mBtnAdd.setOnClickListener {
            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, RC_NEW_WORD)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_NEW_WORD && resultCode == RESULT_OK) {
            data?.getStringExtra(NewWordActivity.NEW_WORD_KEY)?.let {
                val word = Word(it)
                mViewModel.insert(word)
            }
        }
    }
}