package com.example.crud_test.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.RoomDatabase
import com.example.crud_test.Data.Contact
import com.example.crud_test.Models.ContactDataBase
import com.example.crud_test.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetails : AppCompatActivity() {

    private lateinit var database: ContactDataBase
    private lateinit var contact: Contact
    private lateinit var contactLD: LiveData<Contact>

    //Declare UI
    private lateinit var tvName: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvEmail: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)

        //Init UI
        tvName = findViewById(R.id.tv_contact_view_name)
        tvPhone = findViewById(R.id.tv_contact_view_phone)
        tvEmail = findViewById(R.id.tv_contact_view_email)
        btnEdit = findViewById(R.id.btn_edit)
        btnDelete = findViewById(R.id.btn_delete)

        //Database setup
        database = ContactDataBase.getDatabase(this)

        //Get data from DB
        val idContact = intent.getIntExtra("id", 0)
        contactLD = database.contactos().get(idContact)
        contactLD.observe(this, Observer {
            contact = it

            tvName.text = contact.name
            tvPhone.text = contact.phone
            tvEmail.text = contact.email
        })

        //Delete Button
        btnDelete.setOnClickListener {
            contactLD.removeObservers(this)

            CoroutineScope(Dispatchers.IO).launch {
                database.contactos().delete(contact)
                this@ContactDetails.finish()
            }
        }

        //Edit Button
        btnEdit.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }

    }
}