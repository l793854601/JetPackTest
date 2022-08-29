package com.tkm.room.contact

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(vararg contacts: Contact)

    @Delete
    suspend fun delete(vararg contacts: Contact)

    @Update
    suspend fun update(vararg contacts: Contact)

    @Query("DELETE FROM contact")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact")
    fun getAll(): LiveData<List<Contact>>
}