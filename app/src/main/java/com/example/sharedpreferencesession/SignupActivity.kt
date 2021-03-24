package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignupActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val NAME_KEY = "name_key"
    private val PASSWORD_KEY = "password_key"
    lateinit var sName: String
    lateinit var sPass: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val login = findViewById<TextView>(R.id.login)
        val signbtn = findViewById<Button>(R.id.signbtn)
        val name = findViewById<EditText>(R.id.name)
        val pass = findViewById<EditText>(R.id.pass)
        val day = findViewById<EditText>(R.id.day)
        val month = findViewById<EditText>(R.id.month)
        val year = findViewById<EditText>(R.id.year)
        val mobile = findViewById<EditText>(R.id.mobile)
        val city = findViewById<EditText>(R.id.city)
        val state = findViewById<EditText>(R.id.state)
        val country = findViewById<EditText>(R.id.country)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        sName = sharedPreferences.getString(NAME_KEY, null).toString()
        sPass = sharedPreferences.getString(PASSWORD_KEY, null).toString()

        login.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        signbtn.setOnClickListener {
            val username = name.text.toString()
            val password = pass.text.toString()
            val day = day.text.toString()
            val month = month.text.toString()
            val year = year.text.toString()
            val mobile = mobile.text.toString()
            val city = city.text.toString()
            val state = state.text.toString()
            val country = country.text.toString()
            val editor:SharedPreferences.Editor = sharedPreferences.edit()

            editor.putString(NAME_KEY, username)
            editor.putString(PASSWORD_KEY, password)
            editor.putString("day", day)
            editor.putString("month", month)
            editor.putString("year", year)
            editor.putString("mobile", mobile)
            editor.putString("city", city)
            editor.putString("state", state)
            editor.putString("country", country)
            editor.apply()
            editor.commit()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}