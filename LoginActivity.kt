package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.ETC1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPrefFile = "kotlinsharedpreference"
        val signup = findViewById<TextView>(R.id.signup)
        val logbtn = findViewById<Button>(R.id.logbtn)
        val name = findViewById<EditText>(R.id.user_name)
        val pass = findViewById<EditText>(R.id.password)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        signup.setOnClickListener {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
            finish()
        }

        logbtn.setOnClickListener {
            val username = name.text.toString()
            val password = pass.text.toString()
            val editor:SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("name", username)
            editor.putString("pass", password)
            editor.apply()
            editor.commit()

            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
            finish()
        }
    }
    /*
    val sharedIdValue = sharedPreferences.getInt("id_key",0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            if(sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                outputName.setText("default name: ${sharedNameValue}").toString()
                outputId.setText("default id: ${sharedIdValue.toString()}")
            }else{
                outputName.setText(sharedNameValue).toString()
                outputId.setText(sharedIdValue.toString())
     */
}