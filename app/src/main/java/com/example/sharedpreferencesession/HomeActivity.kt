package com.example.sharedpreferencesession

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val SHARED_PREFS = "shared_prefs"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navControl: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navControl = navHostFragment.navController
        sharedPreferences = this.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        toolbar = findViewById(R.id.toolbar)

        AppBarConfiguration(
                setOf(
                        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
                )
                )

        NavigationUI.setupWithNavController(navView, navControl)
        navView.setOnNavigationItemSelectedListener(this)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.log_out) {
                    Toast.makeText(applicationContext, "Log-out Successfully", Toast.LENGTH_LONG).show()
                    editor.clear()
                    editor.apply()
                    editor.commit()
                    editor.putString("isLoggedIn", "false")
                    editor.commit()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
                false
            }
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.mobile_navigation, true).build()
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.navigation_home)
                true
            }
            R.id.navigation_dashboard -> {
                if (isValidDestination(R.id.navigation_dashboard)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.navigation_dashboard)
                }
                true
            }
            R.id.navigation_notifications -> {
                if (isValidDestination(R.id.navigation_notifications)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.navigation_notifications)
                }
                supportActionBar?.title = "Notification Fragment"
                true
            }
            else -> false
        }
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination!!.id
    }
}
