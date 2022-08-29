package com.tkm.room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.tkm.room.basic.MainActivity
import com.tkm.room.contact.ContactListActivity
import com.tkm.room.words.WordListActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<Button>(R.id.btn_basic).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_word).setOnClickListener {
            val intent = Intent(this, WordListActivity::class.java)
            startActivity(intent)
        }


        findViewById<Button>(R.id.btn_contact).setOnClickListener {
            val intent = Intent(this, ContactListActivity::class.java)
            startActivity(intent)
        }
    }
}