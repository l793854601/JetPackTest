package com.tkm.room

import android.app.Application
import com.tkm.room.contact.ContactDatabase
import com.tkm.room.contact.ContactRepository
import com.tkm.room.words.WordDatabase
import com.tkm.room.words.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val wordDatabase by lazy { WordDatabase.getDatabase(this, applicationScope) }
    val wordRepository by lazy { WordRepository(wordDatabase.wordDao()) }

    val contactDatabase by lazy { ContactDatabase.getInstance(this, applicationScope) }
    val contactRepository by lazy { ContactRepository(contactDatabase.getContactDao()) }
}