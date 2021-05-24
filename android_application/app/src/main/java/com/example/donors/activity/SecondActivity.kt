package com.example.donors.activity

import android.app.DownloadManager
import android.app.VoiceInteractor
import android.content.Intent
import android.hardware.usb.UsbEndpoint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.donors.R
import com.example.donors.constant.BEDS
import com.example.donors.constant.OXYGEN
import com.example.donors.constant.PLASMA
import com.example.donors.data.CurrentUserInfo
import com.example.donors.data.PlasmaData
import com.example.donors.databinding.ActivitySecondBinding
import com.example.donors.databinding.AlertDailogDonateBinding
import com.example.donors.library.postDataUsingVolley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_second.*
import org.json.JSONObject

class SecondActivity : AppCompatActivity() {

	lateinit var binding : ActivitySecondBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivitySecondBinding.inflate( layoutInflater )

		setContentView( binding.root )

		val type = intent.getStringExtra("TYPE") as String

		if( type != PLASMA )
			binding.secondCwDonate.visibility = View.GONE

		binding.secondCwDonate.setOnClickListener {

			val dialogLayout = layoutInflater.inflate( R.layout.alert_dailog_donate , null )
			val bloodGroup = dialogLayout.findViewById<EditText>(R.id.alert_et_BG)
			val city = dialogLayout.findViewById<EditText>(R.id.alert_et_CITY)
			val isChecked = dialogLayout.findViewById<CheckBox>(R.id.alert_cb)

			val displayTextAlertDialog = MaterialAlertDialogBuilder( this ).apply {
				setView( dialogLayout )
				setPositiveButton("SUBMIT"){ _ , _ ->
					if( ! isChecked.isChecked )
						Toast.makeText(this@SecondActivity , "Check !" , Toast.LENGTH_LONG).show()
					else
						submitDetails( type , bloodGroup.text.toString() , city.text.toString() )
				}
				setNegativeButton("CANCEL") {_ , _ -> }
			}.create()

			displayTextAlertDialog.show()

		}

		binding.secondCwQuery.setOnClickListener {
			val dialogLayout = layoutInflater.inflate( R.layout.alert_dailog_donate , null )
			val bloodGroup = dialogLayout.findViewById<EditText>(R.id.alert_et_BG)
			val city = dialogLayout.findViewById<EditText>(R.id.alert_et_CITY)
			val isChecked = dialogLayout.findViewById<CheckBox>(R.id.alert_cb)
			if( type != PLASMA ) bloodGroup.visibility = View.GONE

			val displayTextAlertDialog = MaterialAlertDialogBuilder( this ).apply {
				setView( dialogLayout )
				setPositiveButton("SEARCH"){ _ , _ ->
					if( ! isChecked.isChecked )
						Toast.makeText(this@SecondActivity , "Check !" , Toast.LENGTH_LONG).show()
					else
						searchDetails( type , bloodGroup.text.toString() , city.text.toString() )
				}
				setNegativeButton("CANCEL") {_ , _ -> }
			}.create()

			displayTextAlertDialog.show()

		}

	}

	private fun submitDetails(type: String ,bloodGroup: String, city: String) {
		val number = "NOT_DEFINED"
		val plasmaData = PlasmaData( number , bloodGroup , city )
		//Firebase data add
		val db:FirebaseDatabase= FirebaseDatabase.getInstance("https://donors-fc754-default-rtdb.firebaseio.com/")
		val root=db.getReference("Plasma");
		root.child(CurrentUserInfo.getUID()).child("email").setValue(plasmaData.getEmail());
		root.child(CurrentUserInfo.getUID()).child("number").setValue(plasmaData.getNumber());
		root.child(CurrentUserInfo.getUID()).child("locale").setValue(plasmaData.getLocale());
		root.child(CurrentUserInfo.getUID()).child("blood").setValue(plasmaData.getBlood());
		// end
		//val postdata: postDataUsingVolley= postDataUsingVolley();
		//postdata.postDataUsingVolley(this,CurrentUserInfo.getUID(),plasmaData.getEmail(),plasmaData.getNumber(),plasmaData.getLocale(),plasmaData.getBlood());


	}



	private fun searchDetails(type : String ,bloodGroup: String?, city: String){
		Intent( this@SecondActivity , ResultActivity::class.java ).apply {
			putExtra("TYPE" , type )
			putExtra("BLOOD_GROUP" , if(type != PLASMA) "NA" else bloodGroup!! )
			putExtra("CITY" , city)
		}.also {
			startActivity( it )
		}
	}
}