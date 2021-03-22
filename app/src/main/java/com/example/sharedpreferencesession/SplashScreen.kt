package com.example.sharedpreferencesession

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Handler().postDelayed( {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 5000)
    }
}