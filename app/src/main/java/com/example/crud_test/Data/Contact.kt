package com.example.crud_test.Data


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "contacts")
class Contact (
    val name: String,
    val phone: String,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    var idContacto: Int = 0
    ) : Serializable {
}