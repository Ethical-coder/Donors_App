package com.example.donors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.donors.databinding.LayoutResultRvBinding
import kotlinx.android.synthetic.main.layout_result_rv.view.*

class RecyclerViewResultAdapter : RecyclerView.Adapter< RecyclerViewResultAdapter.MainViewHolder >( ) {

	var data = mutableListOf<String>()
	lateinit var binding : LayoutResultRvBinding

	inner class MainViewHolder( private val binding: LayoutResultRvBinding) : RecyclerView.ViewHolder( binding.root )

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
		binding = LayoutResultRvBinding.inflate( LayoutInflater.from( parent.context ) )
		return MainViewHolder( binding )
	}

	override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
		holder.itemView.apply {
			result_rv_tw_1.text = data[ position ]
		}
	}

	override fun getItemCount(): Int = data.size

	// deprecated
	fun set( _data : MutableList<String> ){
		this.data = _data
		notifyDataSetChanged()
	}

	fun add( _data : MutableList<String> ){
		this.data.addAll( _data )
		notifyDataSetChanged()
	}
}