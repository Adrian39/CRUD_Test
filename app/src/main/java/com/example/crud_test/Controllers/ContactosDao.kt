package com.example.crud_test.Controllers

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crud_test.Data.Contact

@Dao
interface ContactosDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts WHERE idContacto = :id")
    fun get(id: Int) : LiveData<Contact>

    @Insert
    fun insertAll(vararg contacts: Contact)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}