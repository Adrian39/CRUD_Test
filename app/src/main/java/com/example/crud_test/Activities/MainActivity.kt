package com.example.crud_test.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_test.Data.Contact
import com.example.crud_test.Models.ContactDataBase
import com.example.crud_test.R
import com.example.crud_test.RVAdapters.RVAContactList
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var fabAddContact: FloatingActionButton

    private val contacts = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create database instance
        val database = ContactDataBase.getDatabase(this);

        //Create RecyclerView and FAB
        val recyclerView = findViewById<RecyclerView>(R.id.rv_contacts)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        fabAddContact = findViewById(R.id.fab_add_contact)

        //Get all items from DB
        database.contactos().getAll().observe(this, Observer {
            contacts.clear()
            for (contact in it){
                contacts.add(contact)
            }
            val adapter = RVAContactList(contacts)
            recyclerView.adapter = adapter
        })

        fabAddContact.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }

    }
}