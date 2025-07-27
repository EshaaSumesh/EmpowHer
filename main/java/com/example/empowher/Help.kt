package com.example.empowher

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService


class Help: AppCompatActivity() {

    private var selectedContact: String? = null
    private val contacts = listOf(
        "Emergency Contact 1 - 8248717121",
        "Emergency Contact 2 - 9884485533",
        "Police - 100",
        "child helpline - 1098 ",
        "BlueCross Helpline - 044 4627 4999",
        "Fire - 101",
        "Railway Enquiry - 139",
        "Road Accident Emergency Service - 1073",
        "Women Domestic Abuse - 181",
        "LPG Leak Helpline - 1906",
        "CYBER CRIME HELPLINE - 155620",
        "Ambulance - 102",
        "Mental Health Rehabilitation Helpline - 1800-500-0019"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val listView: ListView = findViewById(R.id.contactListView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, contacts)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_SINGLE

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            selectedContact = contacts[position]
            val phoneNumber = selectedContact!!.split(" - ")[1]
            makePhoneCall(phoneNumber)
        }

        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->
            val contact = contacts[position]
            val phoneNumber = contact.split(" - ")[1]
            copyToClipboard(phoneNumber)
            true
        }
    }

    private fun makePhoneCall(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(dialIntent)
    }

    private fun copyToClipboard(phoneNumber: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("phone number", phoneNumber)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Phone number copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}
