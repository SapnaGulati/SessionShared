package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class LoginActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val NAME_KEY = "name_key"
    private val PASSWORD_KEY = "password_key"
    lateinit var sName: String
    lateinit var sPass: String
    lateinit var sharedPreferences: SharedPreferences
    lateinit var name: EditText
    private lateinit var pass: EditText
    private lateinit var username: String
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<TextView>(R.id.signup)
        val logbtn = findViewById<Button>(R.id.logbtn)
        name = findViewById(R.id.user_name)
        pass = findViewById(R.id.password)
        username = name.text.toString()
        password = pass.text.toString()
        sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        sName = sharedPreferences.getString(NAME_KEY, null).toString()
        sPass = sharedPreferences.getString(PASSWORD_KEY, null).toString()

        signup.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
            finish()
        }

        logbtn.setOnClickListener {
            if (username != null && password != null) {
//                val intentMap: Intent = intent
//                val jsonString = "JSON string"
//                val type = object : TypeToken<HashMap<Int?, Boolean?>?>() {}.type
//                val map: HashMap<String, String> = Gson().fromJson(jsonString, type)
//                val gson: Gson
//                gson.fromJson(map, type)

                val map = getHashMap(sName)
                Toast.makeText(this, "$map", Toast.LENGTH_SHORT).show()
//                if (sName != username && sPass != password) {
//                    if (map!!.contains(username)) {
//                        val key = map.get(name)
//                        val value = map.getValue(name.toString())
//                        Toast.makeText(this, "$key $value", Toast.LENGTH_SHORT).show()
//                        if (key?.equals(name) == true && value!!.equals(pass)) {
//                            Toast.makeText(this, "Logged-in Successfully", Toast.LENGTH_SHORT)
//                                .show()
                            val i = Intent(this, HomeActivity::class.java)
                            startActivity(i)
                            finish()
//                        } else {
//                            Toast.makeText(
//                                this,
//                                "Either user-name or password is incorrect. No such account exists with this user-name and password",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    } else {
//                        Toast.makeText(this, "key : $sName $sPass", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else {
//                Toast.makeText(this, "User-name or password is incorrect.", Toast.LENGTH_SHORT)
//                    .show()
            }
        }
    }

    private fun getHashMap(key: String?): HashMap<String?, String?>? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val gson = Gson()
        val json = prefs.getString(key, "")
        val type = object : TypeToken<HashMap<Int?, String?>?>() {}.type
        return gson.fromJson(json, type)
    }
}