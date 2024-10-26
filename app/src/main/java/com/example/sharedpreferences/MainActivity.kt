package com.example.sharedpreferences

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "LoginPrefs"
    private val IS_LOGGED_IN = "isLoggedIn"
    private val USERNAME = "username"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(IS_LOGGED_IN, false)) {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            setContentView(R.layout.activity_main)
            setupLogin()
        }

    }

    private fun setupLogin() {
        val userNameInput: EditText = findViewById(R.id.userNameInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = userNameInput.text.toString()
            val password = passwordInput.text.toString()

            if( username == "user" && password == "password" ) {
                val editor = sharedPreferences.edit()
                editor.putBoolean(IS_LOGGED_IN, true)
                editor.putString(USERNAME, username)
                editor.apply()

                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}