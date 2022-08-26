package com.tkm.room.words

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.tkm.room.R

class NewWordActivity : AppCompatActivity() {
    companion object {
        const val NEW_WORD_KEY = "NEW_WORD_KEY"
    }

    private lateinit var mEt: EditText
    private lateinit var mBtnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        mEt = findViewById(R.id.et)
        mBtnSave = findViewById(R.id.btn_save)

        mBtnSave.setOnClickListener {
            val word = mEt.text.toString().trim()
            if (word.isNotEmpty()) {
                val intent = Intent()
                intent.putExtra(NEW_WORD_KEY, word)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Please input word", Toast.LENGTH_SHORT).show()
            }
        }
    }
}