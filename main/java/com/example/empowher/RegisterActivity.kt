package com.example.empowher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val fullName = findViewById<EditText>(R.id.full_name)
        val password = findViewById<EditText>(R.id.password)
        val email = findViewById<EditText>(R.id.email)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        val location = findViewById<EditText>(R.id.location)
        val educationalBackground = findViewById<EditText>(R.id.educational_background)
        val occupation = findViewById<EditText>(R.id.occupation)
        val skills = findViewById<EditText>(R.id.skills)
        val termsConditions = findViewById<CheckBox>(R.id.terms_conditions)
        val registerButton = findViewById<Button>(R.id.register_button)

        registerButton.setOnClickListener {
            if (termsConditions.isChecked) {
                sendRegistrationEmail(
                    fullName.text.toString(),
                    password.text.toString(),
                    email.text.toString(),
                    phoneNumber.text.toString(),
                    location.text.toString(),
                    educationalBackground.text.toString(),
                    occupation.text.toString(),
                    skills.text.toString()
                )
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendRegistrationEmail(
        fullName: String,
        password: String,
        email: String,
        phoneNumber: String,
        location: String,
        educationalBackground: String,
        occupation: String,
        skills: String
    ) {
        val adminEmail = "ezaa2108@gmail.com"
        val subject = "New Registration"
        val message = """
            Full Name: $fullName
            Password: $password
            Email: $email
            Phone Number: $phoneNumber
            Location: $location
            Educational Background: $educationalBackground
            Occupation: $occupation
            Skills: $skills
        """.trimIndent()

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(adminEmail))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "No email clients installed on your device", Toast.LENGTH_SHORT).show()
        }
    }
}
