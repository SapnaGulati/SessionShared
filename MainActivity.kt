package com.example.sharedpreferencesession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<TextView>(R.id.login)
        val signup = findViewById<TextView>(R.id.signup)

        login.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        signup.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}