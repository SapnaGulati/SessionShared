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
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class SignupActivity : AppCompatActivity() {
    private val SHARED_PREFS = "shared_prefs"
    private val NAME_KEY = "name_key"
    private val PASSWORD_KEY = "password_key"
//    private val map: HashMap<String, String> = hashMapOf()
    private val mapKey = "map"
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var name: TextView
    lateinit var pass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val login = findViewById<TextView>(R.id.login)
        val signbtn = findViewById<Button>(R.id.signbtn)
//        val day = findViewById<EditText>(R.id.day)
//        val month = findViewById<EditText>(R.id.month)
//        val year = findViewById<EditText>(R.id.year)
//        val mobile = findViewById<EditText>(R.id.mobile)
//        val city = findViewById<EditText>(R.id.city)
//        val state = findViewById<EditText>(R.id.state)
//        val country = findViewById<EditText>(R.id.country)
//        val map: Map<String, Any>

        val map: HashMap<String, String>
        sharedPreferences= this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val sName = sharedPreferences.getString(NAME_KEY, null).toString()
        val sPass = sharedPreferences.getString(PASSWORD_KEY, null).toString()
        name = findViewById<EditText>(R.id.name)
        pass = findViewById<EditText>(R.id.pass)


        login.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        signbtn.setOnClickListener {
//            val username = name.text.toString()
//            val password = pass.text.toString()
//            val day = day.text.toString()
//            val month = month.text.toString()
//            val year = year.text.toString()
//            val mobile = mobile.text.toString()
//            val city = city.text.toString()
//            val state = state.text.toString()
//            val country = country.text.toString()

            val builder = GsonBuilder()
            val gson = builder.enableComplexMapKeySerialization().setPrettyPrinting().create()
            val type = object : TypeToken<HashMap<String?, String?>?>() {}.type
//            val json = gson.toJson(map, type)
//            if (map.containsKey("name"))
//            name.setText(Objects.requireNonNull(map["name"]).toString())
//            if (map.containsKey("pass"))
//                pass.setText(Objects.requireNonNull(map["pass"]).toString())
//            if(map.isEmpty()) {
//                map[name.toString()] = pass.toString()
//                Toast.makeText(this, "Signed-up successfully...", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, LoginActivity::class.java)
//                intent.putExtra("map", map)
//                startActivity(intent)
//                finish()
//            }
//            else if (map.containsKey(name)) {
//                val key = map.get(name)
//                val value = map.getValue(name.toString())
//                if (key?.equals(name) == true && value.equals(pass)) {
//                    Toast.makeText(this, "User already exists... Please log-in to the app...", Toast.LENGTH_SHORT).show()
//                }
//            }
//            else {
//                map[name.toString()] = pass.toString()
//                Toast.makeText(this, "Multi user signed in successfully...", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, LoginActivity::class.java)
//                intent.putExtra("map", json)
//                startActivity(intent)
//                finish()
//            }

            val editor:SharedPreferences.Editor = sharedPreferences.edit()

//            for (s in map.keys) {
//                editor.putString(s, map[s].toString())
//            }

            editor.apply()
            editor.commit()
            val inputMap: MutableMap<String, Any> = HashMap()
            inputMap["name"] = name
            inputMap["pass"] = pass
            saveMap(inputMap)

        }
    }

    private fun loadMap(): Map<String, Any> {
        val outputMap: Map<String, Any> = HashMap()
        if (sharedPreferences != null) {
            val jsonString = sharedPreferences.getString(mapKey, JSONObject().toString())
            val jsonObject = JSONObject(jsonString)
            val keysItr = jsonObject.keys()
            while (keysItr.hasNext()) {
                val key = keysItr.next()
                outputMap[key]
            }
        }
        return outputMap
    }
    fun saveLocal(view: View) {
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
                saveMap(inputMap)
                Toast.makeText(applicationContext, "Saved Locally!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveMap(inputMap: MutableMap<String, Any>) {
        val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences(
            "MyVariables", Context.MODE_PRIVATE
        )
        val jsonObject = JSONObject(inputMap as Map<*, *>)
        val jsonString = jsonObject.toString()
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(mapKey).apply()
        editor.putString(mapKey, jsonString)
        editor.commit()
    }

}