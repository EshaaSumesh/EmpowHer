package com.example.empowher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userIdEditText = findViewById<EditText>(R.id.user_id)
        val pwdEditText = findViewById<EditText>(R.id.pwd)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val userId = userIdEditText.text.toString()
            val pwd = pwdEditText.text.toString()

            if (userId == "admin" && pwd == "demo") {
                Toast.makeText(this, "Login is successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

