package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration


class HomeActivity : AppCompatActivity() {

    private val SHARED_PREFS = "shared_prefs"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        toolbar = findViewById(R.id.toolbar)

        AppBarConfiguration(
                setOf(
                        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
                )
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.log_out) {
                    Toast.makeText(applicationContext, "Log-out Successfully", Toast.LENGTH_LONG).show()
                    editor.clear()
                    editor.apply()
                    editor.commit()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
                false
            }
        }
    }
}