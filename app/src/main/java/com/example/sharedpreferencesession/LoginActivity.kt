package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.File


class LoginActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val NAME_KEY = "name_key"
    private val PASSWORD_KEY = "password_key"
    private val mapKey = "map1"
    private var map: HashMap<String, String> = hashMapOf()
    lateinit var sName: String
    lateinit var sPass: String
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var username: String
    private lateinit var password: String
    private var mapper = ObjectMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signup = findViewById<TextView>(R.id.signup)
        val logbtn = findViewById<Button>(R.id.logbtn)
        val name = findViewById<View>(R.id.user_name) as EditText
        val pass = findViewById<View>(R.id.password) as EditText
        username = name.text.toString().trim()
        password = pass.text.toString().trim()
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
                try {
                    map = mapper.readValue(
                        File(getExternalFilesDir(null), "result.json"),
                        object : TypeReference<Map<String?, Any?>?>() {})
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                if (map.keys.toString().contains(username) && map.values.toString().contains(password)) {
//                    val l: MutableSet<String> = map.keys
//                    if(username in l) {
//                    val value = map.getValue(pass.toString())
//                        Toast.makeText(this, "${map[username]}  ${map.values}", Toast.LENGTH_SHORT).show()
//                    if (map.keys.toString()) {
                        Toast.makeText(this, "Logged-in Successfully", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, HomeActivity::class.java)
                        startActivity(i)
                        finish()
//                    } else {
//                        Toast.makeText(this, "Either user-name or password is incorrect. No such account exists with this user-name and password", Toast.LENGTH_SHORT).show()
//                    }
//                    }
                } else {
                    Toast.makeText(this, "else key : $sName $sPass", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadMap() {
        val l = intent
        val outputMap = l.getSerializableExtra("map")
        if (sharedPreferences != null) {
            val jsonString = sharedPreferences.getString(mapKey, JSONObject().toString())
            val jsonObject = JSONObject(jsonString)
            val keysItr = jsonObject.keys()
            getHashMap(key = outputMap as HashMap<String, String>?)
            while (keysItr.hasNext()) {
                val key = keysItr.next()
                outputMap?.set(key, key)
            }
        }
    }

    private fun getHashMap(key: HashMap<String, String>?): Map<String, String> {
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val gson = Gson()
//        val json = prefs.getString(key.toString(), "")
        val json = gson.toJson(map)
        val type = object : TypeToken<HashMap<String?, String?>?>() {}.type
        //        val emptyMap = gson.toJson(map) as HashMap<String, String>
//        return gson.fromJson(
//                sharedPreferences.getString(key, emptyList),
//                object : TypeToken<ArrayList<ProductData>>() {
//                }.type
//        )
        return gson.fromJson<HashMap<String, String>?>(json, type)
    }
}
