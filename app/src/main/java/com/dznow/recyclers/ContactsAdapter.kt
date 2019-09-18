package com.dznow.recyclers

import android.support.v7.widget.RecyclerView
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.dznow.R
import com.dznow.models.ContactModel
import kotlinx.android.synthetic.main.layout_contact_preview.view.*
import com.dznow.services.helpers.App

class ContactsAdapter (private val contacts: ArrayList<ContactModel>, message : String) :
    RecyclerView.Adapter<ContactsAdapter.ContactsHolder>() {

    val message = message

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_contact_preview, parent, false)
        return ContactsHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {
        val contact = contacts[position]
        holder.name.text = contact.name
        holder.number.text = contact.number
        holder.email.text = contact.email
    }

    inner class ContactsHolder(itemView: View, var contact: ContactModel? = null) :
        RecyclerView.ViewHolder(itemView) {
        var name : TextView
        var number : TextView
        var email : TextView
        var buttonSMS : ImageButton

        init {
            name = itemView.tv_contact_name
            number = itemView.tv_contact_number
            email = itemView.tv_contact_email
            buttonSMS = itemView.buttonSMS
            buttonSMS.setOnClickListener{
                var smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(number.text.toString(), null, message, null, null)
                Toast.makeText(App.appContext, "Message envoyé avec succès", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
