package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.Serializable


class LoginActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val NAME_KEY = "name_key"
    private val PASSWORD_KEY = "password_key"
    private val mapKey = "map1"
    private lateinit var hashMap: HashMap<String, String>
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
//            l.getStringExtra(getHashMap(map))
            if (username != null && password != null) {
//                val intentMap: Intent = intent
//                val jsonString = "JSON string"
//                val type = object : TypeToken<HashMap<Int?, Boolean?>?>() {}.type
//                val map: HashMap<String, String> = Gson().fromJson(jsonString, type)
//                val gson: Gson
//                gson.fromJson(map, type)

                val mapObj = loadMap()
//                Toast . makeText (this, "$map", Toast.LENGTH_LONG).show()
                Log.d("TAG", mapObj.toString())
//                val mapObj: Map<String, String> = getHashMap(map as HashMap<String, String>?)
                if (mapObj!![sName] != username && mapObj[sPass] != password) {
                    if (mapObj.keys!!.contains(username)) {
                        val key = mapObj.get(name)
                        val value = mapObj.getValue(name.toString())
                        Toast.makeText(this, "$key $value", Toast.LENGTH_SHORT).show()
                        if (key?.equals(name) == true && value!!.equals(pass)) {
                            Toast.makeText(this, "Logged-in Successfully", Toast.LENGTH_SHORT).show()
                            val i = Intent(this, HomeActivity::class.java)
                            startActivity(i)
                            finish()
                        } else {
                            Toast.makeText(
                                    this,
                                    "Either user-name or password is incorrect. No such account exists with this user-name and password",
                                    Toast.LENGTH_SHORT
                            ).show()
//                val map = l.getSerializableExtra("map")
                        }
                    } else {
                        Toast.makeText(this, "key : $sName $sPass", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "User-name or password is incorrect.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun loadMap() {
        val l = intent
        val outputMap = l.getSerializableExtra("map")
//        val outputMap: Map<String, Any> = HashMap()
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
        val json = gson.toJson(hashMap)
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
