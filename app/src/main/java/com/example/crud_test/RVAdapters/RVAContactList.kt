package com.example.crud_test.RVAdapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_test.Activities.ContactDetails
import com.example.crud_test.Data.Contact
import com.example.crud_test.R

class RVAContactList(private val contacts: ArrayList<Contact>)
    : RecyclerView.Adapter<RVAContactList.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contact, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]
        holder.tvName.text = contact.name
        holder.contactID = contact.idContacto
    }

    override fun getItemCount() = contacts.size

    class ViewHolder (v: View) : RecyclerView.ViewHolder(v), View.OnClickListener{
        private var view: View = v

        val tvName: TextView = v.findViewById(R.id.tv_contact_name)
        var contactID: Int? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val intent = Intent(view.context, ContactDetails::class.java)
            intent.putExtra("id", contactID)
            view.context.startActivity(intent)
        }
    }
}