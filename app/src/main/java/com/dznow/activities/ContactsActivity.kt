package com.dznow.activities

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.dznow.R
import com.dznow.models.ContactModel
import com.dznow.recyclers.ContactsAdapter
import com.dznow.services.WEBSITE

class ContactsActivity : AppCompatActivity() {

    private lateinit var contactsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        askPermission()
        contactsRecyclerView = this.findViewById(R.id.rv_contacts) as RecyclerView
        contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        val message = WEBSITE + intent.getStringExtra("url")
        contactsRecyclerView.adapter = ContactsAdapter(getContacts(), message)
    }

    private fun getContacts() : ArrayList<ContactModel> {
        val contacts = ArrayList<ContactModel>()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME)
        cursor!!.moveToFirst()
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS))
            println("$name $number $email")
            contacts.add(ContactModel(name, number, email))
        }
        cursor.close()
        return contacts
    }

    private fun askPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),2)
            }
        }
    }
}
