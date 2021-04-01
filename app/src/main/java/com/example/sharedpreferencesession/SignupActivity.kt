package com.example.sharedpreferencesession

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.io.IOException

class SignupActivity : AppCompatActivity() {
    private val map: HashMap<String, String> = hashMapOf()
    private var mapper = ObjectMapper()
    lateinit var name: TextView
    lateinit var pass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val login = findViewById<TextView>(R.id.login)
        val signbtn = findViewById<Button>(R.id.signbtn)

        name = findViewById<View>(R.id.name) as EditText
        pass = findViewById<View>(R.id.pass) as EditText

        login.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        signbtn.setOnClickListener {
            saveLocal()
        }
    }

    private fun saveLocal() {
        val mName = name.text.toString().trim()
        val mPass = pass.text.toString().trim()
        when {
            mName.isEmpty() -> {
                name.error = "*required"
                name.requestFocus()
            }
            mPass.isEmpty() -> {
                pass.error = "*required"
                pass.requestFocus()
            }
            else -> {
                map[mName] = mPass
                try {
                    mapper.writeValue(File(getExternalFilesDir(null), "result.json"), map)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                Toast.makeText(applicationContext, "Account Created Successfully!!! Log-in to continue...", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
