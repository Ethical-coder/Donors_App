package com.example.donors.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.donors.R
import com.example.donors.adapter.RecyclerViewResultAdapter
import com.example.donors.databinding.ActivityResultBinding
import com.example.donors.library.StatesRecyclerViewAdapter
import com.example.donors.library.EndlessRecyclerViewScrollListener
import kotlinx.coroutines.delay


class ResultActivity : AppCompatActivity() {

	lateinit var binding : ActivityResultBinding

	lateinit var adapter : RecyclerViewResultAdapter
	lateinit var scrollListener: EndlessRecyclerViewScrollListener

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityResultBinding.inflate( layoutInflater )

		setContentView( binding.root )

		var type : String = intent.getStringExtra("TYPE") as String
		var bloodGroup : String = intent.getStringExtra("BLOOD_GROUP") as String
		var city : String = intent.getStringExtra("CITY") as String

		// state change adapter
		val layoutManager = LinearLayoutManager(this)
		binding.resultRvMain.layoutManager = layoutManager
		adapter = RecyclerViewResultAdapter()
		val loadingView = layoutInflater.inflate( R.layout.layout_loading , binding.resultRvMain , false )
		val errorView = layoutInflater.inflate( R.layout.layout_error , binding.resultRvMain , false )
		val emptyView = layoutInflater.inflate( R.layout.layout_empty , binding.resultRvMain , false )

		val statesRecyclerViewAdapter = StatesRecyclerViewAdapter( adapter , loadingView , emptyView , errorView )
		binding.resultRvMain.adapter = statesRecyclerViewAdapter

		// Default
		statesRecyclerViewAdapter.state = StatesRecyclerViewAdapter.STATE_LOADING

		// scroll listener
		scrollListener = object : EndlessRecyclerViewScrollListener( layoutManager ){
			override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
				loadNextDataFromAPI( page , totalItemsCount )
			}
		}
		binding.resultRvMain.addOnScrollListener( scrollListener )

		val initialData : MutableList<String> = getDataFromAPI( bloodGroup , city )
		if( initialData.isNotEmpty() ){
			adapter.set( initialData )
			statesRecyclerViewAdapter.state = StatesRecyclerViewAdapter.STATE_NORMAL
		}else {
			statesRecyclerViewAdapter.state = StatesRecyclerViewAdapter.STATE_EMPTY
		}

	}
	private var test = 10
	private fun loadNextDataFromAPI(page: Int, totalItemsCount: Int) {
		if( test > 20 ) return
		adapter.add(IntRange(test , test + 10).map { it.toString() }.toMutableList())
		test += 10
		Toast.makeText(this ,"new content added !" , Toast.LENGTH_LONG).show()
	}

	private fun getDataFromAPI(bloodGroup: String, city: String): MutableList<String> {
		return IntRange(1 , 10).map { it.toString() }.toMutableList()
	}
}