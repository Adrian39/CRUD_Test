package com.example.crud_test.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.crud_test.Data.Contact
import com.example.crud_test.Models.ContactDataBase
import com.example.crud_test.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddContact : AppCompatActivity() {

    //Declare UI
    private lateinit var btnSave : Button
    private lateinit var etName : EditText
    private lateinit var etEmail : EditText
    private lateinit var etPhone : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        var idContact: Int? = null

        //Init UI
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPhone = findViewById(R.id.et_phone)

        if (intent.hasExtra("contact")) {
            val contact = intent.extras?.getSerializable("contact") as Contact

            etName.setText(contact.name)
            etEmail.setText(contact.email)
            etPhone.setText(contact.phone)

            idContact = contact.idContacto
        }

        btnSave = findViewById(R.id.btn_save)

        val database = ContactDataBase.getDatabase(this);

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            val contact = Contact(name, phone, email)

            if (idContact != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    contact.idContacto = idContact

                    database.contactos().update(contact)
                    this@AddContact.finish()
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    database.contactos().insertAll(contact)

                    this@AddContact.finish()
                }
            }
        }
    }
}