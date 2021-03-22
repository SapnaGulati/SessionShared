package com.example.sharedpreferencesession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<TextView>(R.id.signup)

        signup.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}