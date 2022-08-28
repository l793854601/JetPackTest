package com.tkm.room.contact

import androidx.lifecycle.LiveData

class ContactRepository(private val dao: ContactDao) {

    suspend fun insert(vararg contacts: Contact) {
        dao.insert(*contacts)
    }

    suspend fun delete(vararg contacts: Contact) {
        dao.delete(*contacts)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun update(vararg contacts: Contact) {
        dao.update(*contacts)
    }

    fun getAll(): LiveData<List<Contact>> {
        return dao.getAll()
    }
}