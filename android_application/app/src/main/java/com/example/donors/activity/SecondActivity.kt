package com.example.donors.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.donors.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_second)

		val type = intent.getStringExtra("TYPE") as String

		second_tw_1.text = type
	}
}