package com.example.donors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.donors.R
import kotlinx.android.synthetic.main.activity_main_rv_main.view.*

class ActivityMainRVAdapter : RecyclerView.Adapter< ActivityMainRVAdapter.MainViewHolder >( ) {

	lateinit var  data : List<String>

	inner class MainViewHolder( itemView: View) : RecyclerView.ViewHolder( itemView )

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
		val view : View = LayoutInflater.from( parent.context ).inflate( R.layout.activity_main_rv_main , parent , false)
		return MainViewHolder( view )
	}

	override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
		holder.itemView.apply {
			main_rv_main_tw_head.text = data[position]
		}
	}

	override fun getItemCount(): Int {
		if( this::data.isInitialized ) return data.size
		return 0
	}

	fun set( data : List<String> ){
		this.data = data
		notifyDataSetChanged()
	}

	private fun deleteAll(viewHolder: RecyclerView.ViewHolder){

	}

	fun deleteOneOrAll(viewHolder: RecyclerView.ViewHolder){

	}
}