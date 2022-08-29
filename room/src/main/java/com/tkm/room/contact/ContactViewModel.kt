package com.tkm.room.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {

    val allContacts by lazy { repository.getAll() }

    fun insert(vararg contacts: Contact) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insert(*contacts)
            }
        }
    }

    fun delete(vararg contacts: Contact) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.delete(*contacts)
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteAll()
            }
        }
    }

    fun update(vararg contacts: Contact) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.update(*contacts)
            }
        }
    }
}