package com.example.donors.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.donors.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

	lateinit var binding : ActivityResultBinding
	lateinit var bloodGroup : String
	lateinit var city : String
	lateinit var type : String

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityResultBinding.inflate( layoutInflater )

		setContentView( binding.root )

//		type = intent.getStringExtra("TYPE") as String
		bloodGroup = intent.getStringExtra("BLOOD_GROUP") as String
		city = intent.getStringExtra("CITY") as String

		binding.resultTw.text = bloodGroup + city

	}
}