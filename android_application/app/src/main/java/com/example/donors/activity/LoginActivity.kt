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
import com.example.donors.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val email = binding.LoginEmail
        val pswd = binding.LoginPassowrd
        val button = binding.LoginBtn
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

            val reg = binding.RegisterNavBtn
            reg.setOnClickListener {
                val intent = Intent(this, SignupActivity::class.java)
                startActivity(intent)
            }
        }
    }
}