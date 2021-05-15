package com.example.donors.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.donors.MainActivity
import com.example.donors.R
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.LoginEmail)
        val pswd = findViewById<EditText>(R.id.LoginPassowrd)
        val button = findViewById<Button>(R.id.LoginBtn)
        button.setOnClickListener {
            when {
                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(pswd.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val em = email.text.toString().trim { it <= ' ' }
                    val pwd = pswd.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(em, pwd)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Logged in successfully!", Toast.LENGTH_SHORT)
                                    .show()
                                Intent(this@LoginActivity , MainActivity::class.java).also {
                                    startActivity( it )
                                }
                            } else {
                                Toast.makeText(
                                    this,
                                    "Invalid Email or Password!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                }
            }

            val reg = findViewById<TextView>(R.id.RegisterNavBtn)
            reg.setOnClickListener {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
        }
    }
}