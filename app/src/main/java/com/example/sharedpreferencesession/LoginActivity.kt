package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

class LoginActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val NAME_KEY = "name_key"
    private val PASSWORD_KEY = "password_key"
    private var map: HashMap<String, String> = hashMapOf()
    lateinit var sName: String
    lateinit var sPass: String
    lateinit var sharedPreferences: SharedPreferences
    private var mapper = ObjectMapper()
    lateinit var name: TextView
    lateinit var pass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<TextView>(R.id.signup)
        val logbtn = findViewById<Button>(R.id.logbtn)
        name = findViewById<View>(R.id.user_name) as EditText
        pass = findViewById<View>(R.id.password) as EditText
        sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        sName = sharedPreferences.getString(NAME_KEY, null).toString()
        sPass = sharedPreferences.getString(PASSWORD_KEY, null).toString()

        signup.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
            finish()
        }

        logbtn.setOnClickListener {
            val username = name.text.toString().trim()
            val password = pass.text.toString().trim()
            if (username != null && password != null) {
                try {
                    map = mapper.readValue(
                        File(getExternalFilesDir(null), "result.json"),
                        object : TypeReference<Map<String?, Any?>?>() {})
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                if (map.keys.toString().contains(username) && map.values.toString().contains(password)) {
                    if (map[username] == password) {
                        editor.putString("isLoggedIn", "true")
                        editor.apply()
                        Toast.makeText(this, "Logged-in Successfully", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, HomeActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    else {
                        Toast.makeText(this, "Either user-name or password is incorrect. No such account exists with this user-name and password", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Not such account exists... Please create your account then try to log-in.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
