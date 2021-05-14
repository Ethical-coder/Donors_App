package com.example.donors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.android.gms.tasks.OnCanceledListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val email=findViewById<TextView>(R.id.RegEmail)
        val pswd=findViewById<TextView>(R.id.RegPwd)
        val reg=findViewById<Button>(R.id.RegBtn)
        val login=findViewById<TextView>(R.id.LoginNavBtn)

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