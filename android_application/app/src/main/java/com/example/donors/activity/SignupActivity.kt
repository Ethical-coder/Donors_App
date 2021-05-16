package com.example.donors.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.donors.R
import com.example.donors.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate( layoutInflater )

        setContentView( binding.root )

        val email = binding.RegEmail
        val pswd = binding.RegPwd
        val reg = binding.RegBtn
        val login = binding.LoginNavBtn

        reg.setOnClickListener {
            when{
                TextUtils.isEmpty(email.text.toString().trim { it<= ' ' })->{
                    Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(pswd.text.toString().trim { it<=' ' })->{
                    Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show()
                }
                else->{
                    val em=email.text.toString().trim { it<= ' ' }
                    val pwd=pswd.text.toString().trim { it<=' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(em,pwd).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT)
                                .show()
                            onBackPressed()
                        } else {
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }


        }
        login.setOnClickListener {
            onBackPressed()
        }

    }
}