package com.example.donors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.donors.R
import kotlinx.android.synthetic.main.layout_result_rv.view.*

class RecyclerViewResultAdapter : RecyclerView.Adapter< RecyclerViewResultAdapter.MainViewHolder >( ) {

	lateinit var data : MutableList<String>

	inner class MainViewHolder( itemView: View) : RecyclerView.ViewHolder( itemView )

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
		val view : View = LayoutInflater.from( parent.context ).inflate( R.layout.layout_result_rv , parent , false )
		return MainViewHolder( view )
	}

	override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
		holder.itemView.apply {
			result_rv_tw_1.text = data[ position ]
		}
	}

	override fun getItemCount(): Int {
		if( this::data.isInitialized ) return data.size
		return 0
	}

	fun set( _data : MutableList<String> ){
		this.data = _data
		notifyDataSetChanged()
	}

	fun add( _data : MutableList<String> ){
		this.data.addAll( _data )
		notifyDataSetChanged()
	}
}