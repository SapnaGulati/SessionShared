package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val NAME_KEY = "name_key"
    private val PASSWORD_KEY = "password_key"
    lateinit var sName: String
    lateinit var sPass: String
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<TextView>(R.id.signup)
        val logbtn = findViewById<Button>(R.id.logbtn)
        sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        sName = sharedPreferences.getString(NAME_KEY, null).toString()
        sPass = sharedPreferences.getString(PASSWORD_KEY, null).toString()

            signup.setOnClickListener {
                val i = Intent(this, SignupActivity::class.java)
                startActivity(i)
                finish()
            }

            logbtn.setOnClickListener {
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                val name = findViewById<EditText>(R.id.user_name)
                val pass = findViewById<EditText>(R.id.password)
                val username = name.text.toString()
                val password = pass.text.toString()
                editor.putString("name", username)
                editor.putString("pass", password)
                editor.apply()
                editor.commit()
                if (sName == username && sPass == password) {
                    Toast.makeText(this, "Logged-in Successfully", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else {
                    Toast.makeText(this, "Either user-name or password is incorrect. No such account exists with this user-name and password", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onStart() {
        super.onStart()
        if (sName != null && sPass != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}