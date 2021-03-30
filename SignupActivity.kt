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
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlin.collections.HashMap


class SignupActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val map: HashMap<String, String> = hashMapOf()
    private val mapKey = "map1"
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var name: TextView
    lateinit var pass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val login = findViewById<TextView>(R.id.login)
        val signbtn = findViewById<Button>(R.id.signbtn)

        name = findViewById<EditText>(R.id.name)
        pass = findViewById<EditText>(R.id.pass)

        login.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        signbtn.setOnClickListener {
            saveLocal()
        }
    }

    fun saveLocal() {
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
                val inputMap: MutableMap<String, Any> = HashMap()
                inputMap["name"] = name
                inputMap["pass"] = pass
                saveMap(map)
                Toast.makeText(applicationContext, "Saved Locally!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveMap(inputMap: HashMap<String, String>) {
        sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val builder = GsonBuilder()
        val gson = builder.enableComplexMapKeySerialization().setPrettyPrinting().create()
        val type = object : TypeToken<HashMap<String?, String?>?>() {}.type
        val json = gson.toJson(map, type)
        val MapData = gson.toJson(json)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(mapKey).apply()
        editor.putString(json, MapData)
        editor.commit()
        editor.apply()
        Toast.makeText(this, "Signed-up successfully...", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("map", MapData)
        startActivity(intent)
        finish()
    }

}
