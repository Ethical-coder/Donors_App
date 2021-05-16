package com.example.donors.data

import com.google.firebase.auth.FirebaseUser

object CurrentUserInfo {

	lateinit var user : FirebaseUser


	fun getUID() : String{
		if( this::user.isInitialized ) return user.uid
		return "NOT_INITIALIZED_USER"
	}

	fun getEmail() : String{
		if( this::user.isInitialized ) return user.email.toString()
		return "NOT_INITIALIZED_USER"
	}
}