package com.tkm.room.contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.tkm.room.R

class NewContactActivity : AppCompatActivity() {
    companion object {
        const val CONTACT_KEY = "CONTACT_KEY"

        fun newInstance(context: Context, contact: Contact): Intent {
            val intent = Intent(context, NewContactActivity::class.java)
            intent.putExtra(CONTACT_KEY, contact)
            return intent
        }
    }

    private var mContact: Contact? = null

    private lateinit var mEtFirstName: EditText
    private lateinit var mEtLastName: EditText
    private lateinit var mEtPhone: EditText
    private lateinit var mBtnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contact)

        val contact = intent.getSerializableExtra(CONTACT_KEY) as? Contact
        mContact = contact ?: Contact()

        mEtFirstName = findViewById(R.id.et_first_name)
        mEtLastName = findViewById(R.id.et_last_name)
        mEtPhone = findViewById(R.id.et_phone)
        mBtnSave = findViewById(R.id.btn_save)

        mEtFirstName.setText(mContact?.firstName)
        mEtLastName.setText(mContact?.lastName)
        mEtPhone.setText(mContact?.phone)

        mEtFirstName.addTextChangedListener {
            mContact?.firstName = it.toString().trim()
        }

        mEtLastName.addTextChangedListener {
            mContact?.lastName = it.toString().trim()
        }

        mEtPhone.addTextChangedListener {
            mContact?.phone = it.toString().trim()
        }

        mBtnSave.setOnClickListener {
            mContact?.let {
                val data = Intent()
                data.putExtra(CONTACT_KEY, it)
                setResult(RESULT_OK, data)
                finish()
            }
        }
    }
}