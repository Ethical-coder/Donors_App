package com.example.donors.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.donors.R
import com.example.donors.adapter.RecyclerViewResultAdapter
import com.example.donors.data.PlasmaData
import com.example.donors.databinding.ActivityResultBinding
import com.example.donors.library.StatesRecyclerViewAdapter
import com.example.donors.library.EndlessRecyclerViewScrollListener
import com.example.donors.library.RecyclerItemClickListenr
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.*


class ResultActivity : AppCompatActivity() {

	lateinit var binding : ActivityResultBinding

	lateinit var adapter : RecyclerViewResultAdapter
	lateinit var scrollListener: EndlessRecyclerViewScrollListener
	lateinit var statesRecyclerViewAdapter : StatesRecyclerViewAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityResultBinding.inflate( layoutInflater )

		setContentView( binding.root )

		var type : String = intent.getStringExtra("TYPE") as String
		var bloodGroup : String = intent.getStringExtra("BLOOD_GROUP") as String
		var city : String = intent.getStringExtra("CITY") as String

		// recycler view definitions
		// state change adapter
		val layoutManager = LinearLayoutManager(this)
		binding.resultRvMain.layoutManager = layoutManager
		adapter = RecyclerViewResultAdapter()
		val loadingView = layoutInflater.inflate( R.layout.layout_loading , binding.resultRvMain , false )
		val errorView = layoutInflater.inflate( R.layout.layout_error , binding.resultRvMain , false )
		val emptyView = layoutInflater.inflate( R.layout.layout_empty , binding.resultRvMain , false )

		statesRecyclerViewAdapter = StatesRecyclerViewAdapter( adapter , loadingView , emptyView , errorView )
		binding.resultRvMain.adapter = statesRecyclerViewAdapter

		// Default
		statesRecyclerViewAdapter.state = StatesRecyclerViewAdapter.STATE_LOADING

		// scroll listener
		scrollListener = object : EndlessRecyclerViewScrollListener( layoutManager ){
			override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
				loadNextData( page , totalItemsCount , type , bloodGroup , city)
			}
		}
		binding.resultRvMain.addOnScrollListener( scrollListener )

		binding.resultRvMain.addOnItemTouchListener(
			RecyclerItemClickListenr(
				this ,
				binding.resultRvMain ,
				object : RecyclerItemClickListenr.OnItemClickListener {
					override fun onItemClick(view: View, position: Int) {
						Toast.makeText(this@ResultActivity , "${adapter.data[position]} clicked", Toast.LENGTH_LONG).show()
					}
					override fun onItemLongClick(view: View?, position: Int) {
						Toast.makeText(this@ResultActivity , "${adapter.data[position]} long clicked", Toast.LENGTH_LONG).show()
					}
				}
			)
		)

		loadNextData(0 , 0 , type , bloodGroup , city ) // initial load of data

	}

	private var canLoadMore : Boolean = true
	private val DELAY : Long = 0

	private fun addDataToAdapter( data : MutableList<String> ){
		adapter.add( data )
		//if( adapter.itemCount != 0 )
			statesRecyclerViewAdapter.state = StatesRecyclerViewAdapter.STATE_NORMAL;
		/*else
			statesRecyclerViewAdapter.state = StatesRecyclerViewAdapter.STATE_EMPTY*/
	}

	private fun loadNextDataFromAPI(page: Int, totalItemsCount: Int , type: String , bloodGroup: String, city: String) {
		addDataToAdapter( getDataFromAPI( type, bloodGroup, city ) )
		//Toast.makeText(this ,"new content added !" , Toast.LENGTH_LONG).show()
	}

	private fun loadNextData( page: Int, totalItemsCount: Int , type : String , bloodGroup: String, city: String){
		if( ! canLoadMore ) return
		Handler( Looper.getMainLooper() ).postDelayed({
			loadNextDataFromAPI( page , totalItemsCount , type , bloodGroup , city )
		} , DELAY) // delayed activation
	}

	private fun getDataFromAPI(type: String, bloodGroup: String, city: String): MutableList<String> {
		// Do magic here
		var firebase:FirebaseDatabase= FirebaseDatabase.getInstance("https://donors-fc754-default-rtdb.firebaseio.com/");
		var data=mutableListOf<String>() ;
		var ref:DatabaseReference=firebase.getReference("Plasma");
		ref.addValueEventListener(object:ValueEventListener{
			override fun onDataChange(snapshot: DataSnapshot) {

				for (snap in snapshot.children){
					var value=snap.getValue() as Map<String,String>;
					var temp:String="";
					temp+="Email : "+value.get("email").toString()+"\n";
					temp+="Blood Group : "+value.get("blood").toString()+"\n";
					temp+="City : "+value.get("locale").toString();
					Log.d("datatemp",temp);
					adapter.add(mutableListOf(temp));
				}
			}

			override fun onCancelled(error: DatabaseError) {
			}

		})
		//val data = IntRange(1 , 10).map { it.toString() }.toMutableList()
		//if( data.isEmpty() )
		Log.d("datasnap",data.toString());
		canLoadMore = false;
		return data;
	}
}