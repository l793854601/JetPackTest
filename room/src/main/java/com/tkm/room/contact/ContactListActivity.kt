package com.tkm.room.contact

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tkm.room.App
import com.tkm.room.R

class ContactListActivity : AppCompatActivity() {
    companion object {
        private const val RC_ADD = 100
        private const val RC_UPDATE = 101
    }

    private lateinit var mRv: RecyclerView
    private lateinit var mBtnAdd: FloatingActionButton

    private val mAdapter by lazy { ContactListAdapter() }
    private val mViewModel by lazy { ViewModelProvider(this, ContactViewModelFactory((application as App).contactRepository))[ContactViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        mRv = findViewById(R.id.rv)
        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter = mAdapter

        mBtnAdd = findViewById(R.id.btn_add)
        mBtnAdd.setOnClickListener {
            val intent = Intent(this, NewContactActivity::class.java)
            startActivityForResult(intent, RC_ADD)
        }

        mAdapter.setOnItemClickedListener { _, contact ->
            val intent = NewContactActivity.newInstance(this, contact)
            startActivityForResult(intent, RC_UPDATE)
        }

        mViewModel.allContacts.observe(this) {
            mAdapter.submitList(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_ADD && resultCode == RESULT_OK) {
            val contact = data?.getSerializableExtra(NewContactActivity.CONTACT_KEY) as? Contact
            contact?.let { mViewModel.insert(contact) }
        }
        else if (requestCode == RC_UPDATE && resultCode == RESULT_OK) {
            val contact = data?.getSerializableExtra(NewContactActivity.CONTACT_KEY) as? Contact
            contact?.let { mViewModel.update(contact) }
        }
    }
}